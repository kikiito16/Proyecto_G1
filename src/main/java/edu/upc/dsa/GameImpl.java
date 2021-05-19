package edu.upc.dsa;

import java.util.*;

import edu.upc.dsa.models.*;
import edu.upc.dsa.models.Map;
import edu.upc.dsa.models.Object;
import org.apache.log4j.Logger;

public class GameImpl implements GameInterface{
    private static GameInterface instance;
    final static Logger logger = Logger.getLogger(GameImpl.class);
    HashMap<String, Player> hmPlayers;
    public List<Object> objectsList;
    public List<Game> gamesList;
    public List<Map> mapsList;
    public List<Player> playersList;
    public List<String> connectedList;


    private GameImpl() {
        this.connectedList = new LinkedList<>();
        this.playersList = new LinkedList<>();
        this.objectsList =new LinkedList<>();
        this.gamesList = new LinkedList<>();
        this.mapsList =new LinkedList<>();
        this.hmPlayers=new HashMap<>();
    }

    public static GameInterface getInstance(){
        if (instance==null) instance = new GameImpl();
        return instance;
    }

    //Devuelve  0  --> inicio correcto
    //          -1 --> password incorrecto
    //          -2 --> usuario no encontrado
    @Override
    public int logIn(String username, String password) {
        int error = -1;
        int i = 0;
        boolean found = false;
        while (!found && i < playersList.size()) {
            if (username.equals(playersList.get(i).getUsername()) && password.equals(playersList.get(i).getPassword())) {
                logger.info("Inicio de sesion correcto.");
                addConnected(username);
                error = 0;
                found = true;
            }
            else if (username.equals(playersList.get(i).getUsername()) && password!=playersList.get(i).getPassword()) {
                logger.info("Contraseña incorrecta.");
                error = -1;
                found = true;
            }
            i++;
        }
        if(!found) {
            logger.info("Usuario no encontrado.");
            return -2;
        }
        else return error;
    }

    @Override
    public int signUp(CompleteCredentials newUsr) {
        String username= newUsr.getUsername();
        int error = 0;
        boolean found = false;
        int i = 0;
        while (!found && i < playersList.size()) {
            if (username.equals(playersList.get(i).getUsername())) {
                error = -1;
                found = true;
            }
            i++;
        }

        if (error == -1) {
            logger.info("Este usuario ya existe.");
            return -1;
        }

        else {
            int id = getIdPlayer();
            Player p= new Player(newUsr, id);
            playersList.add(p);
            addConnected(username);
            logger.info("registro completado como:" + p);
            return 0;        }
    }

    public int getIdPlayer() {
        int max = 0;
        for (Player player : playersList) { if (player.getUserId() > max) max = player.getUserId(); }
        return max+1;
    }

    @Override
    public Player getUser(String username) {
        Player p = null;
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
    public Player setUser(int idUser, String username, String password, int money) {
        Player player = null;
        int i = 0;
        boolean found = false;
        while (!found && i < playersList.size()) {
            if(idUser == playersList.get(i).getUserId()) {
                found = true;
                player = playersList.get(i);
            }
            i++;
        }
        return player;
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
    public void addPlayer(Player p)
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
