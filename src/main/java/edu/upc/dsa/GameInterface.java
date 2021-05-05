package edu.upc.dsa;

import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.Map;
import edu.upc.dsa.models.Object;
import edu.upc.dsa.models.Player;

import java.util.List;

public interface GameInterface {

    public int logIn  (String username, String password);
    public Player signUp (String username, String password);  //inicializar listas o pasarlas?
    public Player getUser (String username);
    //public Player changeName (String username, String password);
    public int log_Out (String username);

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

    public void addPlayer(Player player);

    public void clear();

    public int deletePlayer(String user, String psw);
    public Player setUser(int idUser, String name, String password, int money);

}
