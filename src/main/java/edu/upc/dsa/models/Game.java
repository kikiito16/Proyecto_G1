package edu.upc.dsa.models;

public class Game {
    private int id;
    private int playerId;
    private int duration;
    private int victory; // 0 lost, 1 victory
    private int score;

    public Game(){}

    public Game(int id, int playerId, int duration, int victory, int score) {
        this.id = id;
        this.playerId = playerId;
        this.duration = duration;
        this.victory = victory;
        this.score = score;
    }

    public Game(int playerId, int duration, int victory, int score) {
        this.id = -1;
        this.playerId = playerId;
        this.duration = duration;
        this.victory = victory;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getDuration() {
        return duration;
    }

    public int getVictory() {
        return victory;
    }

    public int getScore() {
        return score;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setVictory(int victory) {
        this.victory = victory;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
