package edu.upc.dsa.models;

public class FullObject {

    int id;
    String name;
    int attack;
    int defense;
    int life;
    int price;
    int quantity;

    public FullObject(int id, String name, int attack, int defense, int life, int price, int quantity)
    {
        this.id = id;
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.life = life;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId()
    {
        return this.id;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getLife() {
        return life;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}
