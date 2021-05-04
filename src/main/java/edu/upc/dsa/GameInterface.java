package edu.upc.dsa;

import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.Map;
import edu.upc.dsa.models.Object;
import edu.upc.dsa.models.Player;

import java.util.List;

public interface GameInterface {
    public String logIn  (String usuario_log, String psw_log);
    public Player signUp (int id_sign, String usuario_sign, String psw_sign, double money_sign);  //inicializar listas o pasarlas?
    public Player getUser (String name_player);
   // public void deletePlayer (String name_del, String psw_del );
    public Player changeName (String name_ch, String psw_ch);
    public Player log_Out (String name_out);

    public Object buyObject (String obj_buy);
    public Object addObject (String obj_add);
    public Object useObject (String obj_use);
    public List<Object> getAllObjects ();
    public List<Object> getUserObjects (String name);

    public Game addGame (String player, Game partida);
    public Game getGame (int id_game);
    public List<Game> getAllGames ();
    public List<Game> getGamesByUser (String user);

    public Map getMap (int id_map);

    public List<String> addConnected (String usu_con);


    public void addPlayer(Player player);

    public void clear();

    public void deletePlayer(Player player_del);

    public Player setUser(String name_player);
}
