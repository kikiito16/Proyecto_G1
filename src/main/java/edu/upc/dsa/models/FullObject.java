package edu.upc.dsa.models;

public class FullObject extends GameObject {

    String name;
    int attack;
    int defense;
    int life;
    String imageURL;
    int price;

    public FullObject() {}

    public FullObject(int id, String name, int attack, int defense, int life, String imageURL, int price, int quantity)
    {
        super.id = id;
        super.quantity = quantity;
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.life = life;
        this.imageURL = imageURL;
        this.price = price;
    }

    public FullObject(int id, String name, int attack, int defense, int life, String imageURL, int price)
    {
        super.id = id;
        super.quantity = -1;
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.life = life;
        this.imageURL = imageURL;
        this.price = price;
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

    public void setName(String name) { this.name = name; }

    public void setAttack(int attack) { this.attack = attack; }

    public void setDefense(int defense) { this.defense = defense; }

    public void setLife(int life) { this.life = life; }

    public void setPrice(int price) { this.price = price; }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
