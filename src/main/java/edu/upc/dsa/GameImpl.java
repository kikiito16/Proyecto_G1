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
        this.lista_conectados= new LinkedList<>();
        this.lista_jugadores= new LinkedList<>();
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
    public int logIn(String usuario_log, String psw_log) {
        int error=0;
        String mensaje="Inicio de sesión correcto";
        for (int j=0;j<lista_jugadores.size();j++)
            {
                if (usuario_log.equals(lista_jugadores.get(j).getName_p()) && psw_log.equals(lista_jugadores.get(j).getPsw()) ) {
                    logger.info("Inicio de sesion correcto.");
                    addConnected(usuario_log);
                    error=1;
                }
            }
        if (error==1)
            return error;
        else {
            logger.info("Error al iniciar sesión");
            return error;

        }
    }

    @Override
    public int signUp(int id_sign, String usuario_sign, String psw_sign, double money_sign) {
        int error=0;
        String mensaje="Registro completado";
            for (int j=0;j<lista_jugadores.size();j++)
            {
                if (usuario_sign.equals(lista_jugadores.get(j).getName_p())) {
                    logger.info("Este usuario ya existe.");
                    mensaje="Este usuario ya existe";
                    error=1;
                }
            }

        if (error==1)
            return error;
        else {
            Player p= new Player(id_sign,usuario_sign,psw_sign,money_sign);
            newPlayer(p);
            addConnected(usuario_sign);
            logger.info("Registro completado como:" + p);
            return error;
        }

    }

    @Override
    public Player getUser(String name_player) {
        int posicion=0;
        for (int j=0;j<lista_jugadores.size();j++)
        {
            if (name_player.equals(lista_jugadores.get(j).getName_p())) {
                posicion=j;
                logger.info("Usuario encontrado.");
                //error=1;
            }
        }

        return this.lista_jugadores.get(posicion);
    }

    @Override
    public Player setUser(String name_player) {
        return null;
    }

    @Override
    public int deletePlayer(Player p_del) {
        int error=0;
        for (int j=0;j<lista_jugadores.size();j++)
        {
            if (p_del.getName_p().equals(lista_jugadores.get(j).getName_p())) {
                logger.info("El usuario " + p_del.getName_p()+ " va a ser eliminado.");
                this.lista_jugadores.remove(j);
                this.lista_conectados.remove(j);
                hmPlayers.remove(j);
                logger.info("Esta es la lista jugadores ahora: " + this.lista_jugadores);
                logger.info("Esta es la lista conectados ahora: " + this.lista_conectados);
                logger.info("Este es el hash map ahora: " + this.hmPlayers);
                error=1;
            }
        }
        if (error==0)
        {
            logger.info("No se ha podido encontrar este usuario");
        }
        else
            logger.info("El usuario " + p_del.getName_p() + " ha sido eliminado.");

        return error;
    }

    @Override
    public Player changeName(String name_ch, String psw_ch) {
        return null;
    }

    @Override
    public Player log_Out(String name_out) {
        return null;
    }

    @Override
    public Object buyObject(String obj_buy) {
        return null;
    }

    @Override
    public Object addObject(String obj_add) {
        return null;
    }

    @Override
    public Object useObject(String obj_use) {
        return null;
    }

    @Override
    public List<Object> getAllObjects() {
        return null;
    }

    @Override
    public List<Object> getUserObjects(String name) {
        return null;
    }

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
    public List<String> addConnected(String usu_con) {
        this.lista_conectados.add(usu_con);
        logger.info("Estos son los usuarios conectados: " + this.lista_conectados);
        return this.lista_conectados;
    }

    @Override
    public void addPlayer(Player player) {

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

    //public void deletePlayer()
}
