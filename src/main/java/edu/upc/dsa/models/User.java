package edu.upc.dsa.models;

import edu.upc.dsa.models.api.CompleteCredentials;

public class User {
    private int id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private int money;
	
	public User(){}

    public User(String username, String password, int id) {
        this.setUsername(username);
        this.setPassword(password);
        this.setMoney(50); //Default value for new player
    }

    public User(CompleteCredentials newUsr, int id) {
        this.setUsername(newUsr.getUsername());
        this.setPassword(newUsr.getPassword());
        this.setFullName(newUsr.getFullName());
        this.setEmail(newUsr.getEmail());
        this.setMoney(50); //Default value for new player
    }

    public User(String username, String password, String fullName, String email) {
        this.id = -1;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.money = 50;
    }

    public User(String username, String password, String fullName, String email, int money) {
        this.id = 0;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.money = money;
    }

    public User(String username, String fullName, String email, int money, int ID) {
        this.id = ID;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.money = money;
    }

    public User(String username, String password, String fullName, String email, int money, int id) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
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
