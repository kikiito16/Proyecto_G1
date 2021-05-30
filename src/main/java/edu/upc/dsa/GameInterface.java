package edu.upc.dsa;

import edu.upc.dsa.models.*;
import edu.upc.dsa.models.Object;
import edu.upc.dsa.models.api.CompleteCredentials;

import java.util.List;

public interface GameInterface {

    public int logIn  (String username, String password);
    public int signUp (CompleteCredentials newUsr);  //inicializar listas o pasarlas?
    public User getUser (String username);
    public User getUser (int ID);
    //public Player changeName (String username, String password);
    public int logOut(String username);
    public int updateUser(int id, String username, String fullName, String email, int money);
    public int updateUserAttribute(int id, String attribute, java.lang.Object value);

    public Object buyObject (String object);
    public Object addObject (String object);
    public Object useObject (String object);

    public List<Object> getAllObjects ();
    public List<Object> getUserObjects (String name);

    public Game addGame (String player, Game game);
    public Game getGame (int idGame);
    public List<Game> getAllGames ();
    public List<Game> getGamesByUser (String user);

    public Map getMap (int idMap);

    public List<String> addConnected (String user);
    public void Disconnect(int pos);

    public void addPlayer(User user);

    public void clear();

    public int deletePlayer(String user, String psw);
    public User setUser(int idUser, String name, String password, int money);

}
