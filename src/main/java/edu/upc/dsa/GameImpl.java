package edu.upc.dsa;

import java.security.NoSuchAlgorithmException;
import java.util.*;

import edu.upc.dsa.database.UserDAO;
import edu.upc.dsa.database.UserDAOImpl;
import edu.upc.dsa.models.*;
import edu.upc.dsa.models.Map;
import edu.upc.dsa.models.GameObject;
import edu.upc.dsa.models.api.CompleteCredentials;
import org.apache.log4j.Logger;

public class GameImpl implements GameInterface{
    private static GameInterface instance;
    final static Logger logger = Logger.getLogger(GameImpl.class);
    private final UserDAO dao;

    private GameImpl() {
        this.dao = new UserDAOImpl();
    }

    public static GameInterface getInstance(){
        if (instance==null) instance = new GameImpl();
        return instance;
    }

    @Override
    public int logIn(String username, String password) throws NoSuchAlgorithmException{
        //Return  0  --> Successful
        //          -1 --> No match in data
        //          -2 --> error database
        int res = dao.logIn(username, password);
        if(res >= 0) {
            logger.info(username + " logged in successfully!");
        }

        return res;
    }

    @Override
    public int signUp(CompleteCredentials user) {
        //-1 --> username already exists
        //others --> user id
        int res = dao.addUser(user.getUsername(), user.getPassword(), user.getFullName(), user.getEmail());
        if(res != -1) {
            logger.info(user.getUsername() + " signed up successfully!");
            List<GameObject> list = new LinkedList<>();
            list.add(new GameObject(6, 5));
            list.add(new GameObject(4, 1));
            list.add(new GameObject(10, 1));
            int res2 = dao.addToInventory(list, res);
            if (res2 == 0) logger.info("User ID " + res + ": Default objects added successfully!");
        }

        return res;
    }

    @Override
    public User getUser(int ID) {
        User user = dao.getUser(ID);
        logger.info("Get user:" + user);
        return user;
    }

    @Override
    public int deleteUser(int id) {
        //0 successful
        //-1 sql error
        int res = dao.deleteUser(id);
        if(res == 0) logger.info("User ID " + id + " deleted successfully!");
        else logger.info("User ID " + id + " NOT deleted due to an error");
        return res;
    }

    @Override
    public int updateUser(int id, String username, String fullName, String email, int money) {
        // 0 successful
        // -1 error
        int res = dao.updateUser(id, username, fullName, email, money);
        if (res == 0) logger.info("User ID " + id + " updated successfully!");
        return res;
    }

    @Override
    public int updateUserAttribute(int id, String attribute, java.lang.Object value) {
        //-1 error
        //0 successful
        int res = dao.updateUserAttribute(id, attribute, value);
        if (res == 0) logger.info("User ID " + id + ": " + attribute + "updated successfully!");
        return res;
    }

    @Override
    public int buyObject(List<GameObject> object, int id) {
        logger.info("User ID " + id + ": Objects bought successfully! (DEMO)");
        return 0;
    }

    @Override
    public int sellObject(List<GameObject> object, int id) {
        logger.info("User ID " + id + ": Objects sold successfully! (DEMO)");
        return 0;
    }

    @Override
    public int addObject(List<GameObject> objectList, int userId) {
        //0 successful
        //-1 or -2 error
        int res = dao.addToInventory(objectList, userId);
        if (res == 0) logger.info("User ID " + userId + ": Objects added successfully!");
        return res;
    }

    @Override
    public int useObject(int objectId, int userId) {
        int res = dao.useObject(objectId, userId);
        if(res == 0) logger.info("User ID " + userId + ": Object used successfully!");
        else logger.info("User ID " + userId + ": Object " + objectId + " not used due to an error");
        return res;
    }

    @Override
    public List<FullObject> getUserObjects(int userId) {
        List<FullObject> res = dao.getInventoryOf(userId);
        if (res != null) logger.info("Get user objects (id = " + userId + "): " + res);
        return res;
    }

    @Override
    public List<FullObject> getStoreObjects() {
        //null --> error
        //If successful, it returns the list of the objects, including the max quantity available to buy
        return dao.getStoreObjects();
    }

    @Override
    public int addGame(int playerId, int duration, int victory, int score) {
        //0 successful
        //-1 incorrect playerId
        //-2 error
        int res = dao.addGame(playerId, duration, victory, score);
        if (res == 0) logger.info("Player " + playerId + ": Game added successfully!");
        return res;
    }

    @Override
    public Game getGame(int id) {
        //Returns game if found
        //null if no results
        Game res = dao.getGame(id);
        if (res != null) logger.info("Get game " + id + ": " + res);
        return res;
    }

    @Override
    public List<Game> getAllGamesOf(int playerId) {
        //null -> not found
        List<Game> res = dao.getAllGamesOf(playerId);
        if (res != null) logger.info("Get games of player " + playerId + ": " + res);
        return res;
    }

    @Override
    public Map getMap(int id_map) {
        return null;
    }
}
