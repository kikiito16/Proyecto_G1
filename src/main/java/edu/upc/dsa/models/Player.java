package edu.upc.dsa.models;
//import edu.upc.dsa.models.Object;

import java.util.List;

public class Player {
    private int id_player;
    private String name_p;
    private String psw;
    private double money;
    private List<Object> lista_objetos;
    private List<Game> lista_games;

    public Player(int id_p, String name_p, String psw, double money) {
        this.setId_player(id_p);
        this.setName_p(name_p);
        this.setPsw(psw);
        this.setMoney(money);
    }

    public int getId_player() {
        return id_player;
    }

    public void setId_player(int id_player) {
        this.id_player = id_player;
    }

    public String getName_p() {
        return name_p;
    }

    public void setName_p(String name_p) {
        this.name_p = name_p;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public List<Object> getLista_objetos() {
        return lista_objetos;
    }

    public void setLista_objetos(List<Object> lista_objetos) {
        this.lista_objetos = lista_objetos;
    }

    public List<Game> getLista_games() {
        return lista_games;
    }

    public void setLista_games(List<Game> lista_games) {
        this.lista_games = lista_games;
    }

    public String toString() {
        return id_player + ", " + name_p;
    }
}
