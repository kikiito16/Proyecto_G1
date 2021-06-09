package edu.upc.dsa;

import java.util.*;

import edu.upc.dsa.database.UserDAO;
import edu.upc.dsa.database.UserDAOImpl;
import edu.upc.dsa.models.*;
import edu.upc.dsa.models.Map;
import edu.upc.dsa.models.Object;
import edu.upc.dsa.models.api.CompleteCredentials;
import org.apache.log4j.Logger;

public class GameImpl implements GameInterface{
    private static GameInterface instance;
    final static Logger logger = Logger.getLogger(GameImpl.class);
    public List<String> connectedList;
    private final UserDAO dao;

    private GameImpl() {
        this.connectedList = new LinkedList<>();
        this.dao = new UserDAOImpl();
    }

    public static GameInterface getInstance(){
        if (instance==null) instance = new GameImpl();
        return instance;
    }

    @Override
    public int logIn(String username, String password) {
        //Return  0  --> Successful
        //          -1 --> No match in data
        //          -2 --> error database
        int res = dao.logIn(username, password);
        if(res >= 0) addConnected(username);

        return res;
    }

    @Override
    public int signUp(CompleteCredentials user) {
        //-1 --> username already exists
        //others --> user id
        int res = dao.addUser(user.getUsername(), user.getPassword(), user.getFullName(), user.getEmail());
        if(res != -1) addConnected(user.getUsername());

        return res;
    }

    @Override
    public int logOut(String username) {
        int error = -1;
        int i = 0;
        boolean found = false;
        if (connectedList.size()>0) {
            while (!found && i < connectedList.size()) {
                if (username.equals(connectedList.get(i))) {
                    logger.info("Successfully disconnected: " + username);
                    Disconnect(i);
                    error = 0;
                    found = true;
                }
                i++;
            }
        }
        if (error==-1)
            logger.info("Could not disconnect: " + username);

        return error;
    }

    @Override
    public User getUser(int ID) {
        return dao.getUser(ID);
    }

    @Override
    public int deleteUser(int id) {
        //0 successful
        //-1 incorrect password
        //-2 error
        return dao.deleteUser(id);
    }

    @Override
    public int updateUser(int id, String username, String fullName, String email, int money) {
        // 0 successful
        // -1 error
        return dao.updateUser(id, username, fullName, email, money);
    }

    @Override
    public int updateUserAttribute(int id, String attribute, java.lang.Object value) {
        //-1 error
        //0 successful
        return dao.updateUserAttribute(id, attribute, value);
    }

    @Override
    public Object buyObject(String object) {
        return null;
    }

    @Override
    public int addObject(List<Object> objectList, int userId) {
        //0 successful
        //-1 error
        return dao.addToInventory(objectList, userId);
    }

    @Override
    public Object useObject(String object) {
        return null;
    }

    @Override
    public List<FullObject> getUserObjects(int userId) {
        return dao.getInventoryOf(userId);
    }

    @Override
    public int addGame(int playerId, int duration, int victory, int score) {
        //0 successful
        //-1 incorrect playerId
        //-2 error
        return dao.addGame(playerId, duration, victory, score);
    }

    @Override
    public Game getGame(int id) {
        //Returns game if found
        //null if no results
        return dao.getGame(id);
    }

    @Override
    public List<Game> getAllGamesOf(int playerId) {
        //null -> not found
        return dao.getAllGamesOf(playerId);
    }

    @Override
    public Map getMap(int id_map) {
        return null;
    }

    @Override
    public List<String> addConnected(String username) {
        this.connectedList.add(username);
        logger.info("Current users connected: " + this.connectedList);
        return this.connectedList;
    }

    @Override
    public void Disconnect (int pos) {
        this.connectedList.remove(pos);

    }

    @Override
    public void clear() {
        connectedList.clear();
    }
}
