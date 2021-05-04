package edu.upc.dsa.models;
import java.util.List;

public class Game {
    private int id_game;
    private int id_player;
    private String Victory;
    private double duration;
    private List<Map> lista_mapas;

    public int getId_game() {
        return id_game;
    }

    public void setId_game(int id_game) {
        this.id_game = id_game;
    }

    public int getId_player() {
        return id_player;
    }

    public void setId_player(int id_player) {
        this.id_player = id_player;
    }

    public String getVictory() {
        return Victory;
    }

    public void setVictory(String victory) {
        Victory = victory;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public List<Map> getLista_mapas() {
        return lista_mapas;
    }

    public void setLista_mapas(List<Map> lista_mapas) {
        this.lista_mapas = lista_mapas;
    }
}
