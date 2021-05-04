package edu.upc.dsa;

import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.Map;
import edu.upc.dsa.models.Object;
import edu.upc.dsa.models.Player;

import java.util.List;

public interface GameInterface {
    public Player NewPlayer (int id_p, String name_p, String psw, double money);  //Es necesario pasar las listas vacias o se pueden inicializar solas vacias?
    public Player LogIn  (String usuario_log, String psw_log);
    public Player SignUp (String usuario_sign, String psw_sign);
    public Player GetUser (String name_player);
    public Player DeletePlayer (String name_del, String psw_del );
    public Player ChangeName (String name_ch, String psw_ch);
    public Player Log_Out (String name_out);

    public Object BuyObject (String obj_buy);
    public Object AddObject (String obj_add);
    public Object UseObject (String obj_use);
    public List<Object> GetAllObjects ();
    public List<Object> GetUserObjects (String name);

    public Game AddGame (String player, Game partida);
    public Game GetGame (int id_game);
    public List<Game> GetAllGames ();
    public List<Game> GetGamesByUser (String user);

    public Map GetMap (int id_map);
}
