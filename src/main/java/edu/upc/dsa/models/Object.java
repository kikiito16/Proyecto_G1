package edu.upc.dsa.models;

public class Object {
    private int id_object;
    private int id_owner;
    private int number;
    private double costBuy;
    private double costSell;

    public int getId_object() {
        return id_object;
    }

    public void setId_object(int id_object) {
        this.id_object = id_object;
    }

    public int getId_owner() {
        return id_owner;
    }

    public void setId_owner(int id_owner) {
        this.id_owner = id_owner;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getCostBuy() {
        return costBuy;
    }

    public void setCostBuy(double costBuy) {
        this.costBuy = costBuy;
    }

    public double getCostSell() {
        return costSell;
    }

    public void setCostSell(double costSell) {
        this.costSell = costSell;
    }
}
