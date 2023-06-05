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
        this.neighbours = findAndAddNeighbours(this.board, this.row, this.index);
        this.edges = findOrCreateEdges();
        this.vertices = findOrCreateVertices();
    }


    private Map<String, Tile> findAndAddNeighbours(Tile[][] board, int row, int index) {
        Map<String, Tile> neighbours = new HashMap<>();
        int midRow = (board.length % 2 == 0) ? board.length / 2 : (board.length - 1) / 2;
    
        String[] directions = { "top_left", "top_right", "right", "bottom_right", "bottom_left", "left" };
        String[] oppositeDirections = { "bottom_right", "bottom_left", "left", "top_left", "top_right", "right" };
        int[] rowAdjustments = { -1, -1, 0, 1, 1, 0 };
        int[] colAdjustments = { -1, 0, 1, 1, -1, -1 };
        int[] colAdjustmentsLowerHalf = { 0, 1, 1, 0, -1, -1 };
    
        for (int i = 0; i < 6; i++) {
            int newRow = row + rowAdjustments[i];
            int newCol = (row > midRow) ? index + colAdjustmentsLowerHalf[i] : index + colAdjustments[i];
    
            // Avoid invalid index
            if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[newRow].length) {
                addIfNeighborExists(directions[i], oppositeDirections[i], newRow, newCol, neighbours);
            }
        }
    
        return neighbours;
    }
    
    private void addIfNeighborExists(String direction, String oppositeDirection, int newRow, int newCol, Map<String, Tile> neighbours) {
        Tile neighborTile = board[newRow][newCol];
        if (neighborTile != null) {
            neighbours.put(direction, neighborTile);
            addSelfToNeighbour(neighborTile, oppositeDirection);
        }
    }
    
    private void addSelfToNeighbour(Tile neighbour, String direction) {
        if (neighbour != null) {
            neighbour.neighbours.put(direction, this);
        }
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

        createOraddVertexFromNeighbours("top", "top_left", "bottom_right", 
                                "top_right", "bottom_left", "down", vertices, 1);
        createOraddVertexFromNeighbours("top_right", "top_right", "bottom_left", 
                                "right", "left", "up", vertices, 2);
        createOraddVertexFromNeighbours("bottom_right", "right", "left", 
                                "bottom_right", "top_left", "down", vertices, 3);
        createOraddVertexFromNeighbours("bottom", "bottom_right", "top_left", 
                                "bottom_left", "top_right", "up", vertices, 4);
        createOraddVertexFromNeighbours("bottom_left", "bottom_left", "top_right", 
                                "left", "right", "down", vertices, 5);
        createOraddVertexFromNeighbours("top_left", "left", "right", 
                                "top_left", "bottom_right", "up", vertices, 6);
        
        return vertices;
    }
    

    private void createOraddVertexFromNeighbours(String vertexName, String neighbour1TileKey, String neighbour1EdgeKey, 
                                        String neighbour2TileKey, String neighbour2EdgeKey, 
                                        String vertexDirection, Map<String, Vertex> vertices, int index) {
        // If there are no neighbouring tiles, creates a new vertex and adds it to vertices hash.
        // Then adds the vertex to the leading and lagging edges in the given direction.
        // The leading edge shares the same key as neighbour1TileKey and the lagging with neighbour2Tilekey.
        if (neighbours.get(neighbour1TileKey) == null && neighbours.get(neighbour2TileKey) == null) {
            Vertex vertex = new Vertex();
            vertices.put(vertexName, vertex);
            edges.get(neighbour1TileKey).addVertex(vertex, vertexDirection);
            edges.get(neighbour2TileKey).addVertex(vertex, vertexDirection);
        } 
        // To determine which neighbour contains the vertex, this function will evaluate to false if a neighbour and
        // desired vertex aren't found. Otherwise it will add the vertex and return true. Order of the conditionals ensures
        // that the vertex will be whether it is on the leading, lagging, or both edges.
        else {
            Boolean isVertexAdded = addVertexFromExistingNeighbour(neighbour1TileKey, neighbour1EdgeKey, vertexDirection, vertices, vertexName);
            if (!isVertexAdded) {
                addVertexFromExistingNeighbour(neighbour2TileKey, neighbour2EdgeKey, vertexDirection, vertices, vertexName);
            }
        }
    }


    private Boolean addVertexFromExistingNeighbour(String neighbourTileKey, String neighbourEdgeKey, String vertexDirection, 
                                            Map<String, Vertex> vertices, String vertexName) {
    if ((neighbours.get(neighbourTileKey) != null) &&
        (neighbours.get(neighbourTileKey).getEdge(neighbourEdgeKey).getVertices().containsKey(vertexDirection))) {

        Vertex vertex = neighbours.get(neighbourTileKey).getEdge(neighbourEdgeKey).getVertices().get(vertexDirection);
        vertices.put(vertexName, vertex);
        vertex.addNeighbour(this);

        return true;
    }

    return false;
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
