package com.masaba.settlers.model;

import java.util.ArrayList;
import java.util.List;

public class Edge {
    private List<Tile> neighbours;
    private Road road;

    public Edge() {
        this.neighbours = new ArrayList<>();
        this.road = null;
    }

    public List<Tile> getNeighbours() {
        return neighbours;
    }

    public void addNeighbour(Tile tile) {
        this.neighbours.add(tile);
    }

    public Road getRoad() {
        return road;
    }

    public void setRoad(Road road) {
        this.road = road;
    } 
}
