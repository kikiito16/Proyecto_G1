package edu.upc.dsa.database;

import edu.upc.dsa.models.FullObject;
import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.GameObject;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.api.Inventory;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    @Override
    public int addToInventory(List<GameObject> objectList, int userId)
    {
        int res = -1;
        try {
            session = SessionFactory.openSession();
            for (GameObject o : objectList) {
                Inventory inventory = new Inventory(userId, o.getId(), o.getQuantity());
                res = session.create(inventory);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
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
                    HashMap objectHashmap = session.getBy(Object.class, "id", i.get("objectId"));
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
        int res = -1;
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

        int res = -1;
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
