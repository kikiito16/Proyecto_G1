package edu.upc.dsa.models;

public class PlayerBuy {
    int cost;
    String idPlayer;
    String nameObject;

    public PlayerBuy() {}

    public int getCost() { return cost; }

    public void setCost(int cost) { this.cost = cost; }

    public String getIdPlayer() { return idPlayer; }

    public void setIdPlayer(String idPlayer) { this.idPlayer = idPlayer; }

    public String getNameObject() { return nameObject; }

    public void setNameObject(String nameObject) { this.nameObject = nameObject; }

    @Override
    public String toString() {
        return "Object Bought:" + nameObject +  " by:" + idPlayer + "with a cost:" + cost + ".";}}
