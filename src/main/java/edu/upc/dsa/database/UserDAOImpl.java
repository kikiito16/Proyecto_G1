package edu.upc.dsa.database;

import edu.upc.dsa.models.*;
import edu.upc.dsa.models.api.Inventory;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDAOImpl implements UserDAO{

    private Session session = null;

    //The user is added to the database
    //Return --> id
    //if -1 is returned --> username already exists
    @Override
    public int addUser(String username, String password, String fullName, String email) {

        int userId = -1;

        try
        {
            session = SessionFactory.openSession();

            User user = new User(username, password, fullName, email);
            int res = session.create(user);
            if(res != 0)
                userId = res;
            else
                userId = (Integer) session.getBy(User.class, "username", username).get("id");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            session.close();
        }
        return userId;
    }

    //Returns 0 successful
    // -1 or -2 --> errors
    @Override
    public int addToInventory(List<GameObject> objectList, int userId) {
        int res = -1;
        try {
            session = SessionFactory.openSession();

            for(GameObject o : objectList)
            {
                //We try to update the inventory of the user
                String query = "UPDATE Inventory SET quantity=quantity+? WHERE userId=? AND objectId=?;";
                int affectedRows = session.customUpdate(query, o.getQuantity(), userId, o.getId());

                //If the object does not already exists in the inventory of the user, we INSERT it
                if(affectedRows == 0)
                {
                    Inventory inventory = new Inventory(userId, o.getId(), o.getQuantity());
                    res = session.create(inventory);
                }
                else if(affectedRows == 1) res = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return res;
    }

    //0 successful
    // -1 error
    @Override
    public int useObject(int objectId, int userId) {
        int res = -1;
        try {
            session = SessionFactory.openSession();
            int affectedRows = session.customUpdate(
                    "UPDATE Inventory SET quantity=quantity-1 WHERE objectId=? AND userId=?;",
                    objectId,
                    userId
            );
            if(affectedRows == 1)
                res = 0;
        }
        catch (SQLIntegrityConstraintViolationException e) {
            //The quantity of the object in the inventory table is now 0, so we delete it from the table
            res = session.customDelete(
                    "DELETE FROM Inventory WHERE objectId=? AND userId=?;",
                    objectId,
                    userId
            );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return res;
    }

    @Override
    public List<FullObject> getInventoryOf(int userId) {
        List<FullObject> objectList = new ArrayList<>();
        try
        {
            session = SessionFactory.openSession();
            List<HashMap<String, java.lang.Object>> inventoryTable = session.getAllBy(Inventory.class, "userId", userId);

            if(inventoryTable != null)
                for(HashMap<String, java.lang.Object> i : inventoryTable)
                {
                    HashMap objectHashmap = session.getBy(GameObject.class, "id", i.get("objectId"));
                    if(objectHashmap != null)
                        objectList.add(new FullObject(
                                (int)objectHashmap.get("id"),
                                (String)objectHashmap.get("name"),
                                (int)objectHashmap.get("attack"),
                                (int)objectHashmap.get("defense"),
                                (int)objectHashmap.get("life"),
                                (String)objectHashmap.get("imageURL"),
                                (int)objectHashmap.get("price"),
                                (int)i.get("quantity")
                        ));
                }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return objectList;
    }

    @Override
    public List<FullObject> getStoreObjects() {
        List<FullObject> objects = new ArrayList<>();

        try {
            session = SessionFactory.openSession();
            List<HashMap<String, Object>> list = session.getAll(GameObject.class);

            for (HashMap<String, Object> h : list) {
                objects.add(new FullObject(
                        (int) h.get("id"),
                        (String) h.get("name"),
                        (int) h.get("attack"),
                        (int) h.get("defense"),
                        (int) h.get("life"),
                        (String) h.get("imageURL"),
                        (int) h.get("price")
                ));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
        }

        return objects;
    }

    //-1 error
    //-2 incorrect userId
    //-3 not enough money
    //>0 amount of money of the user after the purchase
    @Override
    public int buyObject(int userId, List<GameObject> objects) {

        int money = -1;
        int totalPrice = 0;

        try
        {
            //We create the sql
            session = SessionFactory.openSession();
            String sql = "SELECT id, price FROM GameObject WHERE ";
            for(int i = 0; i < objects.size()-1; i++)
                sql = sql + "id=? or ";
            sql = sql + "id=?;";

            //We create a list with all the GameObject ids
            List<Object> idList = new ArrayList<>();
            for(GameObject o : objects)
                idList.add(o.getId());

            //The query is executed
            ResultSet resultSet = session.customQuery(sql, idList);

            //We calculate the total price
            while(resultSet.next())
            {
                int id = (int)resultSet.getObject("id");
                int price = (int)resultSet.getObject("price");
                int quantity;

                boolean found = false;
                int i = 0;
                while(i < objects.size() && !found)
                {
                    if(objects.get(i).getId() == id)
                        found = true;
                    else
                        i++;
                }
                if(found)
                    quantity = objects.get(i).getQuantity();
                else
                    return -1;

                totalPrice += quantity * price;
            }

            //We check if the user has enough money
            resultSet = session.customQuery("SELECT money FROM User WHERE id=?;", userId);
            if(!resultSet.first())
                return -2;  //incorrect userId

            money = (int)resultSet.getObject("money");

            if(totalPrice > money)
                //Not enough money
                return -3;

            //If we have enough money, we buy it
            if(addToInventory(objects, userId) == 0)
                session.customUpdate(
                        "UPDATE User SET money=money-" + totalPrice + " WHERE id=?;",
                        userId
                );
            else
                return -1;

        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        finally
        {
            session.close();
        }

        return money-totalPrice;
    }

    //-1 incorrect objectId or userId
    //-2 --> the quantity of objects that the user wants to sell is higher than the quantity he actually has
    //-3 error
    //>0 amount of money of the user after the sale
    @Override
    public int sellObject(int userId, GameObject gameObject) {

        int money = -3;

        try
        {
            session = SessionFactory.openSession();

            //We check if the user has enough objects to sell
            ResultSet resultSet = session.getBy(Inventory.class, "quantity",
                    "userId", userId,
                    "objectId", gameObject.getId()
                    );

            if(!resultSet.first())
                return -1;

            int inventoryQuantity = (int)resultSet.getObject("quantity");

            if(inventoryQuantity < gameObject.getQuantity())
                return -2;

            //Up to this point, the user has quantity enough to sell, so we sell the object(s)
            //We calculate the total price
            resultSet = session.getBy(GameObject.class, "price",
                    "id", gameObject.getId());
            if(!resultSet.first())
                return -1;

            int totalPrice = (int)resultSet.getObject("price")/2 * gameObject.getQuantity();

            //We errase the objects from the Inventory
            for(int i = 0; i < gameObject.getQuantity(); i++)
                if(useObject(gameObject.getId(), userId) == -1)
                    return -1;

            //We pay the player
            int updateRes = session.customUpdate(
                    "UPDATE User SET money=money+" + totalPrice +
                            " WHERE id=?;",
                    userId
            );

            if(updateRes == -1)
                return -1;

            //We will return the money of the user
            resultSet = session.getBy(User.class, "money", "id", userId);
            resultSet.first();
            money = (int)resultSet.getObject("money");

        }
        catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        finally {
            session.close();
        }

        return money;
    }

    //0 successful
    //-1 incorrect playerId
    //-2 error
    @Override
    public int addGame(int playerId, int duration, int victory, int score) {
        Game game = new Game(playerId, duration, victory, score);
        int res = -2;
        try
        {
            session = SessionFactory.openSession();
            res = session.create(game);
        }
        catch (Exception e) {
            e.printStackTrace();
            return res;
        }
        finally {
            session.close();
        }

        return res;
    }

    //Returns game if found
    //null if no results
    @Override
    public Game getGame(int id) {

        Game game = null;

        try
        {
            session = SessionFactory.openSession();
            HashMap<String, java.lang.Object> hashMap = session.getBy(Game.class, "id", id);
            if(hashMap != null)
                game = new Game(
                        (int)hashMap.get("id"),
                        (int)hashMap.get("playerId"),
                        (int)hashMap.get("duration"),
                        (int)hashMap.get("victory"),
                        (int)hashMap.get("score")
                );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
        }

        return game;
    }

    //null -> not found
    @Override
    public List<Game> getAllGamesOf(int playerId) {
        List<Game> gameList = null;

        try
        {
            session = SessionFactory.openSession();
            List<HashMap<String, java.lang.Object>> hashMapList = session.getAllBy(Game.class, "playerId", playerId);

            if(hashMapList != null) {
                gameList = new ArrayList<>();
                for (HashMap<String, java.lang.Object> h : hashMapList)
                    gameList.add(new Game(
                            (int) h.get("id"),
                            (int) h.get("playerId"),
                            (int) h.get("duration"),
                            (int) h.get("victory"),
                            (int) h.get("score")
                    ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
        }

        return gameList;
    }

    //null --> id doesn't exist, or any other error
    @Override
    public Map getMap(int id) {
        Map map = new Map();
        try
        {
            session = SessionFactory.openSession();
            HashMap<String, Object> hashMap = session.getBy(Map.class, "id", id);
            if(hashMap == null)
                return null;

            map.setLine1(hashMap.get("line1").toString());
            map.setLine2(hashMap.get("line2").toString());
            map.setLine3(hashMap.get("line3").toString());
            map.setLine4(hashMap.get("line4").toString());
            map.setLine5(hashMap.get("line5").toString());
            map.setLine6(hashMap.get("line6").toString());
            map.setLine7(hashMap.get("line7").toString());
            map.setLine8(hashMap.get("line8").toString());

        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return map;
    }

    @Override
    public User getUser(int userId) {

        User user = null;
        try
        {
            session = SessionFactory.openSession();

            HashMap<String, java.lang.Object> attributes = session.getBy(User.class, "id", userId);

            if(attributes != null) {
                user = new User(
                        attributes.get("username").toString(),
                        attributes.get("password").toString(),
                        attributes.get("fullName").toString(),
                        attributes.get("email").toString()
                );
                user.setId((int)attributes.get("id"));
                user.setMoney((int)attributes.get("money"));
            }
            else return null;

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            session.close();
        }

        return user;

    }

	@Override
    public User getUser(String username) {
        User user = null;
        try {
            session = SessionFactory.openSession();

            HashMap<String, java.lang.Object> attributes = session.getBy(User.class, "username", username);
            user = new User(
                    attributes.get("username").toString(),
                    attributes.get("password").toString(),
                    attributes.get("fullName").toString(),
                    attributes.get("email").toString(),
                    (Integer) attributes.get("money")
            );
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            session.close();
        }

        return user;
    }

    //-1 error
    //0 successful
    @Override
    public int deleteUser(int id) {
        int res = -1;
        try
        {
            session = SessionFactory.openSession();
            //We delete first all the inventory objects of the user
            int res1 = session.delete(Inventory.class,
                    "userId", id
                    );

            //We delete all the games of the player
            int res2 = -1;
            if(res1 == 0)
                res2 = session.delete(Game.class,
                        "playerId", id
                        );

            //We delete the player
            if(res2 == 0)
                res = session.delete(User.class, id);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        finally {
            session.close();
        }

        return res;
    }

    //-2 --> error database
    //-1 --> login failed
    //0 --> correct login
    @Override
    public int logIn(String username, String password) {

        HashMap<String,java.lang.Object> attributes = null;
        try
        {
            session = SessionFactory.openSession();

            attributes = session.getBy(User.class, "username", username);

        }
        catch(Exception e)
        {
            e.printStackTrace();
            return -2;
        }
        finally {
            session.close();
        }

        if(attributes == null)
            return -1;

        else
        {
            String passwordHash;
            try {
                //MD5 encryption
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] messageDigest = md.digest(password.getBytes());
                BigInteger number = new BigInteger(1, messageDigest);
                passwordHash = number.toString(16);
            }
            catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return -2;
            }

            if(attributes.get("username").equals(username)
                    && attributes.get("password").equals(passwordHash))
                return (Integer) session.getBy(User.class, "username", username).get("id");
        }

        return -1;
    }

    //0 successful
    //-1 error
    @Override
    public int updateUser(int id, String username, String fullName,
                          String email, int money) {
        int res;
        try
        {
            session = SessionFactory.openSession();

            User user = new User(username, null, fullName, email, money, id);

            res = session.update(user);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        finally {
            session.close();
        }

        return res;
    }

    //-1 --> error
    //0 successful
    @Override
    public int updateUserAttribute(int id, String attribute, java.lang.Object value) {

        int res;
        try
        {
            session = SessionFactory.openSession();

            res = session.update(User.class, id, attribute, value);

        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        finally {
            session.close();
        }

        return res;
    }
}
