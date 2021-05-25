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

        return res;
    }

    //-1 --> username already exists
    //others --> user id
    @Override
    public int signUp(CompleteCredentials user) {

        int res = dao.addUser(user.getUsername(), user.getPassword(), user.getFullName(), user.getEmail());

        return res;
    }

    /*public int getIdPlayer() {
        int max = 0;
        for (User user : playersList) { if (user.getId() > max) max = user.getId(); }
        return max+1;
    }*/

    @Override
    public User getUser(String username) {
        User p = null;
        int i = 0;
        boolean found = false;
        while(!found && i < playersList.size()){
            if(username.equals(playersList.get(i).getUsername())) {
                found = true;
                p = playersList.get(i);
                i++;
            }
        }
        return p;
    }

    @Override
    public int deletePlayer(String user, String psw) {
        int error = -1;
        boolean found = false;
        int i = 0;
        int pos=0;
        while(!found && i< playersList.size()) {
            if (user.equals(playersList.get(i).getUsername()) && psw.equals(playersList.get(i).getPassword())) {
                error = 0;
                found = true;
                pos=i;
            }
            i++;
        }
        if (error == -1) logger.info("No se ha podido encontrar este usuario");
        else {
            logger.info("El usuario " + user+ " va a ser eliminado.");
            this.playersList.remove(pos);
            //(this.connectedList.remove(pos);  //Crear busqueda de usuario en conectados
            hmPlayers.remove(user);
            logger.info("Esta es la lista jugadores ahora: " + this.playersList);
            //logger.info("Esta es la lista conectados ahora: " + this.connectedList);
            logger.info("Este es el hash map ahora: " + this.hmPlayers);
            logger.info("El usuario " + user + " ha sido eliminado.");
        }
        return error;
    }

    @Override
    public User setUser(int idUser, String name, String password, int money) {
        return null;
    }


    @Override
    public int log_Out(String username) {
        int error = -1;
        int i = 0;
        boolean found = false;
        if (connectedList.size()>0) {
            while (!found && i < connectedList.size()) {
                if (username.equals(connectedList.get(i))) {
                    logger.info("Desconexión correcta");
                    Disconnect(i);
                    error = 0;
                    found = true;
                }
                i++;
            }
        }
        if (error==-1)
            logger.info("No se ha podido desconectar al usuario.");

        return error;
    }

    @Override
    public Object buyObject(String object) {
        return null;
    }

    @Override
    public Object addObject(String object) {
        return null;
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
