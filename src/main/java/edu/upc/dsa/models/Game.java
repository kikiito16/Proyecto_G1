package edu.upc.dsa.models;
import java.util.List;

public class Game {
    private int idGame;
    private int idPlayer;
    private String victory;
    private double duration;
    private List<Map> mapsList;

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getVictory() {
        return victory;
    }

    public void setVictory(String victory) {
        this.victory = victory;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public List<Map> getMapsList() {
        return mapsList;
    }

    public void setMapsList(List<Map> mapsList) {
        this.mapsList = mapsList;
    }
}
