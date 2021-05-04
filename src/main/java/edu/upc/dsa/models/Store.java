package edu.upc.dsa.models;

import java.util.LinkedList;
import java.util.List;

public class Store {
    private List<Object> objectsList;

    public Store() { this.objectsList = new LinkedList<>(); }

    public List<Object> getObjectsList() {
        return objectsList;
    }

    public void setObjectsList(List<Object> objectsList) {
        this.objectsList = objectsList;
    }
}
