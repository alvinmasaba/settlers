package com.masaba.settlers.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Edge {
    private List<Tile> neighbours;
    private Map<String, Vertex> vertices;
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


    public Map<String, Vertex> getVertices() {
        return vertices;
    }


    public Vertex getVertex(String direction) {
        return vertices.get(direction);
    }


    public void addVertex(Vertex vertex, String direction) {
        this.vertices.put(direction, vertex);
    }
}
