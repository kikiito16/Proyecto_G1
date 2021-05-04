package edu.upc.dsa.models;

public class Map {
    private int id_mapa;
    private double cells;   //double, string, int???

    public int getId_mapa() {
        return id_mapa;
    }

    public void setId_mapa(int id_mapa) {
        this.id_mapa = id_mapa;
    }

    public double getCells() {
        return cells;
    }

    public void setCells(double cells) {
        this.cells = cells;
    }
}
