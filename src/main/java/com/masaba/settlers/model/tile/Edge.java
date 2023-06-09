package com.masaba.settlers.model.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.masaba.settlers.model.Road;

public class Edge {
    private List<Tile> neighbours;
    private Map<String, Vertex> vertices;
    private Road road;

    public Edge() {
        this.neighbours = new ArrayList<>();
        this.road = null;
        this.vertices = new HashMap<>();
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

    public Map<String, Vertex> getVertices() {
        return this.vertices;
    }

    public Vertex getVertex(String direction) {
        return this.vertices.get(direction);
    }

    public void addVertex(Vertex vertex, String direction) {
        if (!this.vertices.containsKey(direction)) {
            this.vertices.put(direction, vertex);
        }
    }
}
