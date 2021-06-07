package edu.upc.dsa.models;

public class FullObject {

    int id;
    String name;
    int attack;
    int defense;
    int life;
    int price;
    int quantity;

    public FullObject() {}

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

    public void setId(int id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setAttack(int attack) { this.attack = attack; }

    public void setDefense(int defense) { this.defense = defense; }

    public void setLife(int life) { this.life = life; }

    public void setPrice(int price) { this.price = price; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}
