package edu.upc.dsa.models.api;

public class Inventory {

    int userId;
    int objectId;
    int quantity;

    public Inventory(int userId, int objectId, int quantity)
    {
        this.userId = userId;
        this.objectId = objectId;
        this.quantity = quantity;
    }

}
