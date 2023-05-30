package com.masaba.settlers.model;

import java.util.*;

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
        this.neighbours = findNeighbours(this.board, this.row, this.index);
        this.edges = findOrCreateEdges();
        this.vertices = findOrCreateVertices();
    }


    private Map<String, Tile> findNeighbours(Tile[][] board, int row, int index) {
        Map<String, Tile> neighbours = new HashMap<>();

        if (row > 0 && index > 0)
            neighbours.put("top_left", board[row - 1][index - 1]);
        if (row > 0 && index < board[row].length - 1)
            neighbours.put("top_right", board[row - 1][index]);
        if (index < board[row].length - 1)
            neighbours.put("right", board[row][index + 1]);
        if (row < board.length - 1 && index < board[row].length - 1)
            neighbours.put("bottom_right", board[row + 1][index]);
        if (row < board.length - 1 && index > 0)
            neighbours.put("bottom_left", board[row + 1][index - 1]);
        if (index > 0)
            neighbours.put("left", board[row][index - 1]);
        
        return neighbours;
    }


    private Map<String, Edge> findOrCreateEdges() {
        Map<String, Edge> edges = new HashMap<>();

        addEdgeFromNeighbour("top_left", "bottom_right", edges);
        addEdgeFromNeighbour("top_right", "bottom_left", edges);
        addEdgeFromNeighbour("right", "left", edges);
        addEdgeFromNeighbour("bottom_right", "top_left", edges);
        addEdgeFromNeighbour("bottom_left", "top_right", edges);
        addEdgeFromNeighbour("left", "right", edges);

        return edges;
    }


    private void addEdgeFromNeighbour(String neighbourKey, String edgeKey, Map<String, Edge> edges) {
        if (neighbours.get(neighbourKey) != null) {
            Edge edge = neighbours.get(neighbourKey).getEdges().get(edgeKey);
            if (edge == null) {
                edge = new Edge();
                edge.addNeighbour(this);
                edge.addNeighbour(neighbours.get(neighbourKey));
            }
            edges.put(neighbourKey, edge);
        }
    }


    private Map<String, Vertex> findOrCreateVertices() {
        // To do
        return new HashMap<>();
    }


    private Map<String, Edge> getEdges() {
        return this.edges;
    }

    
    private Map<String, Vertex> getVertices() {
        return this.vertices;
    } 
}
