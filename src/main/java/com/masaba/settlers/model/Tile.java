package com.masaba.settlers.model;

import java.util.*;
import com.masaba.settlers.factory.*;

public class Tile {
    private int row;
    private int index;
    private Map<String, Tile> neighbours;
    private Map<String, Edge> edges;
    private Map<String, Vertex> vertices;
    private Tile[][] board;


    public Tile(int row, int index, Tile[][] board) {
        this.row = row;
        this.index = index;
        this.board = board;

        NeighbourFactory neighbourFactory = new NeighbourFactory(this, this.board);
        this.neighbours = neighbourFactory.findAndAddNeighbours(this.row, this.index);

        EdgeFactory edgeFactory = new EdgeFactory(this);
        this.edges = edgeFactory.findOrCreateEdges();
        
        VertexFactory vertexFactory = new VertexFactory(this);
        this.vertices = vertexFactory.findOrCreateVertices();
    }


    public void addNeighbour(String direction, Tile neighbour) {
        this.neighbours.put(direction, neighbour);
    }


    public Map<String, Edge> getEdges() {
        return this.edges;
    }


    public Edge getEdge(String value) {
        return this.edges.get(value);
    }


    public Map<String, Vertex> getVertices() {
        return this.vertices;
    }


    public Map<String, Tile> getNeighbours() {
        return this.neighbours;
    }


    public int getRow() {
        return this.row;
    }

    
    public int getIndex() {
        return this.index;
    }
}
