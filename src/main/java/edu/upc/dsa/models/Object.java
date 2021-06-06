package edu.upc.dsa.models;

public class Object {
    int id;
    int quantity;

    public Object(int id, int quantity)
    {
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
}
