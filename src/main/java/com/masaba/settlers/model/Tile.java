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
        // If the neighbour exists, set variable edge to the corresponding edge, set to a newly created edge.
        Edge edge = (neighbours.get(neighbourKey) != null) ? neighbours.get(neighbourKey).getEdges().get(edgeKey) : new Edge();
        edge.addNeighbour(this);
        edges.put(neighbourKey, edge);
    }


    private Map<String, Vertex> findOrCreateVertices() {
        Map<String, Vertex> vertices = new HashMap<>();

        addVertexFromNeighbours("top", "top_left", "bottom_right", 
                                "top_right", "bottom_left", "down", vertices);
        addVertexFromNeighbours("top_right", "top_right", "bottom_left", 
                                "right", "left", "up", vertices);
        addVertexFromNeighbours("bottom_right", "right", "left", 
                                "bottom_right", "top_left", "down", vertices);
        addVertexFromNeighbours("bottom", "bottom_right", "top_left", 
                                "bottom_left", "top_right", "up", vertices);
        addVertexFromNeighbours("bottom_left", "bottom_left", "top_right", 
                                "left", "right", "down", vertices);
        addVertexFromNeighbours("top_left", "left", "right", 
                                "top_left", "bottom_right", "down", vertices);
        
        return vertices;
    }
    

    private void addVertexFromNeighbours(String vertexName, String neighbour1TileKey, String neighbour1EdgeKey, 
                                        String neighbour2TileKey, String neighbour2EdgeKey, 
                                        String vertexDirection, Map<String, Vertex> vertices) {

        if (neighbours.get(neighbour1TileKey) == null && neighbours.get(neighbour2TileKey) == null) {
            // If there are no neighbouring tiles, create a new vertex and add it to vertices hash.
            Vertex vertex = new Vertex();
            vertices.put(vertexName, vertex);
            // Add the vertex to the current tile edge in the given direction.
            edges.get(neighbour1TileKey).addVertex(vertex, vertexDirection);

        } else if ((neighbours.get(neighbour1TileKey) != null) || (neighbours.get(neighbour2TileKey) != null)) {
            // If the neighbour exists, get the edge, the vertex in the given position, add the vertex to vertices array, 
            // and add this tile as a neighbour.
            Vertex vertex = (neighbours.get(neighbour1TileKey) != null) ? neighbours.get(neighbour1TileKey).getEdge(neighbour1EdgeKey).getVertex(vertexDirection) :
                                                                          neighbours.get(neighbour2TileKey).getEdge(neighbour2EdgeKey).getVertex(vertexDirection);
            vertices.put(vertexName, vertex);
            vertex.addNeighbour(this);
        } 
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
}
