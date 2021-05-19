package edu.upc.dsa.models;
//import edu.upc.dsa.models.Object;

import java.sql.DriverManager;
import java.util.List;

public class Player {
    private int userId;
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

    public Player(String username, String password, int userId) {
        this.setUsername(username);
        this.setPassword(password);
        this.setUserId(userId);
        this.setMoney(50.0); //Valor inicial para cada jugador nuevo.

    }

    public Player(CompleteCredentials newUsr, int userId) {
        this.setUsername(newUsr.getUsername());
        this.setPassword(newUsr.getPassword());
        this.setFullName(newUsr.getFullName());
        this.setEmail(newUsr.getEmail());
        this.setUserId(userId);
        this.setMoney(50.0); //Valor inicial para cada jugador nuevo.

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        return userId + ", " + username;
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
