package edu.upc.dsa.models;

public class Object {
    private String idObject;
    private int idOwner;
    private int number;
    private double costBuy;
    private double costSell;

    public String getIdObject() {
        return idObject;
    }

    public void setIdObject(String idObject) {
        this.idObject = idObject;
    }

    public int getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(int idOwner) {
        this.idOwner = idOwner;
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
