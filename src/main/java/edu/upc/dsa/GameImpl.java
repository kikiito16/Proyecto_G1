package edu.upc.dsa;

import java.util.*;

import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.Map;
import edu.upc.dsa.models.Object;
import edu.upc.dsa.models.Player;
import org.apache.log4j.Logger;

public class GameImpl implements GameInterface{
    private static GameInterface instance;
    final static Logger logger = Logger.getLogger(GameImpl.class);
    HashMap<String, Player> hmPlayers;
    public List<Object> lista_objetos;
    public List<Game> lista_partidas;
    public List<Map> lista_mapas;
    public List<Player> lista_jugadores;
    public List<String> lista_conectados;


    private GameImpl() {
        this.lista_objetos=new LinkedList<>();
        this.lista_partidas= new LinkedList<>();
        this.lista_mapas=new LinkedList<>();
        this.hmPlayers=new HashMap<String, Player>();
    }

    public static GameInterface getInstance(){
        if (instance==null) instance = new GameImpl();
        return instance;
    }



    private Player newPlayer(Player player) {
        logger.info("Vamos a añadir al jugador: : " + player);
        this.lista_jugadores.add(player);
        return player;
    }


    @Override
    public String LogIn(String usuario_log, String psw_log) {
        int error=0;
        String mensaje="Inicio de sesión correcto";
        for (int i=0;i<lista_jugadores.size();i++)
        {
            for (int j=0;j<lista_jugadores.size();j++)
            {
                if (usuario_log.equals(lista_jugadores.get(j).getName_p()) && psw_log.equals(lista_jugadores.get(j).getPsw()) ) {
                    //this.lista_conectados.add(usuario_log);
                    addConectado(usuario_log);
                    error=1;
                }
            }
        }
        if (error==1)
            return mensaje;
        else
            return "Error al iniciar sesion";
    }

    @Override
    public String SignUp(int id_sign, String usuario_sign, String psw_sign, double money_sign) {
        int error=0;
        String mensaje="Registro completado";
        for (int i=0;i<lista_jugadores.size();i++)
        {
            for (int j=0;j<lista_jugadores.size();j++)
            {
                if (usuario_sign.equals(lista_jugadores.get(j).getName_p())) {
                    mensaje="Este usuario ya existe";
                    error=1;
                }
            }
        }

        if (error==1)
            return mensaje;
        else {
            Player p= new Player(id_sign,usuario_sign,psw_sign,money_sign);
            newPlayer(p);
            addConectado(usuario_sign);
            logger.info("registro completado como:" + p);
            return mensaje;
        }

    }

    @Override
    public Player GetUser(String name_player) {
        return null;
    }

    @Override
    public Player DeletePlayer(String name_del, String psw_del) {
        return null;
    }

    @Override
    public Player ChangeName(String name_ch, String psw_ch) {
        return null;
    }

    @Override
    public Player Log_Out(String name_out) {
        return null;
    }

    @Override
    public Object BuyObject(String obj_buy) {
        return null;
    }

    @Override
    public Object AddObject(String obj_add) {
        return null;
    }

    @Override
    public Object UseObject(String obj_use) {
        return null;
    }

    @Override
    public List<Object> GetAllObjects() {
        return null;
    }

    @Override
    public List<Object> GetUserObjects(String name) {
        return null;
    }

    @Override
    public Game AddGame(String player, Game partida) {
        return null;
    }

    @Override
    public Game GetGame(int id_game) {
        return null;
    }

    @Override
    public List<Game> GetAllGames() {
        return null;
    }

    @Override
    public List<Game> GetGamesByUser(String user) {
        return null;
    }

    @Override
    public Map GetMap(int id_map) {
        return null;
    }

    @Override
    public List<String> addConectado(String usu_con) {
        this.lista_conectados.add(usu_con);
        logger.info("Estos son los usuarios conectados: ");
        return this.lista_conectados;
    }

    public void addJugador(Player p)
    {
        hmPlayers.put(p.getName_p(),p);
        this.lista_jugadores.add(p);
    }

    @Override
    public void clear() {
        lista_conectados.clear();
        lista_jugadores.clear();
        lista_partidas.clear();
        hmPlayers.clear();
        lista_objetos.clear();
        lista_mapas.clear();
    }
}
