package edu.upc.dsa.models;

public class GameObject {
    int id;
    int quantity;

    public GameObject() {}

    public GameObject(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public int getId()
    {
        return this.id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) { this.id = id; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}
