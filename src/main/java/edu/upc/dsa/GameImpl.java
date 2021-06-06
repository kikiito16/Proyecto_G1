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
    HashMap<String, User> hmPlayers;
    public List<Object> objectsList;
    public List<Game> gamesList;
    public List<Map> mapsList;
    public List<User> playersList;
    public List<String> connectedList;
    private UserDAO dao;


    private GameImpl() {
        this.connectedList = new LinkedList<>();
        this.playersList = new LinkedList<>();
        this.objectsList =new LinkedList<>();
        this.gamesList = new LinkedList<>();
        this.mapsList =new LinkedList<>();
        this.hmPlayers=new HashMap<>();
        this.dao = new UserDAOImpl();
    }

    public static GameInterface getInstance(){
        if (instance==null) instance = new GameImpl();
        return instance;
    }

    //Devuelve  0  --> inicio correcto
    //          -1 --> datos no coinciden
    //          -2 --> error base de datos
    @Override
    public int logIn(String username, String password) {

        int res = dao.logIn(username, password);
        if(res >= 0) addConnected(username);

        return res;
    }

    //-1 --> username already exists
    //others --> user id
    @Override
    public int signUp(CompleteCredentials user) {

        int res = dao.addUser(user.getUsername(), user.getPassword(), user.getFullName(), user.getEmail());
        if(res != -1) addConnected(user.getUsername());

        return res;
    }

    /*public int getIdPlayer() {
        int max = 0;
        for (User user : playersList) { if (user.getId() > max) max = user.getId(); }
        return max+1;
    }*/

    @Override
    public User getUser(String username) {
        return dao.getUser(username);
    }

    @Override
    public User getUser(int ID) {
        return dao.getUser(ID);
    }


    @Override
    public int logOut(String username) {
        int error = -1;
        int i = 0;
        boolean found = false;
        if (connectedList.size()>0) {
            while (!found && i < connectedList.size()) {
                if (username.equals(connectedList.get(i))) {
                    logger.info("Desconexión correcta de " + username);
                    Disconnect(i);
                    error = 0;
                    found = true;
                }
                i++;
            }
        }
        if (error==-1)
            logger.info("No se ha podido desconectar al usuario " + username);

        return error;
    }

    // 0 sucessful
    // -1 error
    @Override
    public int updateUser(int id, String username, String fullName, String email, int money) {
        int res = dao.updateUser(id, username, fullName, email, money);
        return res;
    }


    //-1 error
    //0 successful
    @Override
    public int updateUserAttribute(int id, String attribute, java.lang.Object value) {
        int res = dao.updateUserAttribute(id, attribute, value);
        return res;
    }

    //0 successful
    //-1 incorrect password
    //-2 error
    @Override
    public int deleteUser(int id) {
        int res = dao.deleteUser(id);
        return res;
    }

    @Override
    public User setUser(int idUser, String name, String password, int money) {
        return null;
    }

    @Override
    public Object buyObject(String object) {
        return null;
    }

    //0 successful
    //-1 error
    @Override
    public int addObject(List<Object> objectList, int userId) {
        int res = dao.addToInventory(objectList, userId);
        return res;
    }

    @Override
    public List<FullObject> getAllObjects(int userId) {
        List<FullObject> list = dao.getInventoryOf(userId);

        return list;
    }


    @Override
    public Object useObject(String object) {
        return null;
    }

    @Override
    public List<Object> getAllObjects() {
        return null;
    }

    @Override
    public List<Object> getUserObjects(String name) { return null; }

    @Override
    public Game addGame(String player, Game partida) {
        return null;
    }

    @Override
    public Game getGame(int id_game) {
        return null;
    }

    @Override
    public List<Game> getAllGames() {
        return null;
    }

    @Override
    public List<Game> getGamesByUser(String user) {
        return null;
    }

    @Override
    public Map getMap(int id_map) {
        return null;
    }

    @Override
    public List<String> addConnected(String username) {
        this.connectedList.add(username);
        logger.info("Estos son los usuarios conectados: " + this.connectedList);
        return this.connectedList;
    }

    @Override
    public void Disconnect (int pos) {
        this.connectedList.remove(pos);

    }

    @Override
    public void addPlayer(User p)
    {
        hmPlayers.put(p.getUsername(),p);
        this.playersList.add(p);
    }

    @Override
    public void clear() {
        connectedList.clear();
        playersList.clear();
        gamesList.clear();
        hmPlayers.clear();
        objectsList.clear();
        mapsList.clear();
    }
}
