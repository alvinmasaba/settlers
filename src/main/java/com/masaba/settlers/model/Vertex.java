package com.masaba.settlers.model;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private List<Tile> neighbours;
    private Building building;

    public Vertex() {
        this.neighbours = new ArrayList<>();
        this.building = null;
    }

    public List<Tile> getNeighbours() {
        return neighbours;
    }

    public void addNeighbour(Tile tile) {
        this.neighbours.add(tile);
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.buidling = building;
    }
}
