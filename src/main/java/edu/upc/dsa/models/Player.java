package edu.upc.dsa.models;
//import edu.upc.dsa.models.Object;

import java.util.List;

public class Player {
    private int idPlayer;
    private String username;
    private String password;
    private double money;
    private List<Object> objectsList;
    private List<Game> gamesList;
    private String fullName;
    private String email;

    /*public Player(int idPlayer, String username, String password, double money) {
        this.setIdPlayer(idPlayer);
        this.setUsername(username);
        this.setPassword(password);
        this.setMoney(money);
    }*/

    public Player(String username, String password, int idPlayer) {
        this.setUsername(username);
        this.setPassword(password);
        this.setIdPlayer(idPlayer);
        this.setMoney(50.0); //Valor inicial para cada jugador nuevo.

    }

    public Player(CompleteCredentials newUsr, int idPlayer) {
        this.setUsername(newUsr.getUsername());
        this.setPassword(newUsr.getPassword());
        this.setFullName(newUsr.getFullName());
        this.setEmail(newUsr.getEmail());
        this.setIdPlayer(idPlayer);
        this.setMoney(50.0); //Valor inicial para cada jugador nuevo.

    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public List<Object> getObjectsList() {
        return objectsList;
    }

    public void setObjectsList(List<Object> objectsList) {
        this.objectsList = objectsList;
    }

    public List<Game> getGamesList() {
        return gamesList;
    }

    public void setGamesList(List<Game> gamesList) {
        this.gamesList = gamesList;
    }

    public String toString() {
        return idPlayer + ", " + username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
