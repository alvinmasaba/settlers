package com.masaba.settlers.factory;

import com.masaba.settlers.model.Tile;
import com.masaba.settlers.model.Vertex;
import com.masaba.settlers.model.Edge;

import java.util.HashMap;
import java.util.Map;

public class VertexFactory {
    private Tile tile;

    public VertexFactory(Tile tile) {
        this.tile = tile;
    }

    public Map<String, Vertex> findOrCreateVertices() {
        Map<String, Vertex> vertices = new HashMap<>();

        createOrAddVertexFromNeighbours("top", "top_left", "bottom_right", 
                                "top_right", "bottom_left", "down", vertices);
        createOrAddVertexFromNeighbours("top_right", "top_right", "bottom_left", 
                                "right", "left", "up", vertices);
        createOrAddVertexFromNeighbours("bottom_right", "right", "left", 
                                "bottom_right", "top_left", "down", vertices);
        createOrAddVertexFromNeighbours("bottom", "bottom_right", "top_left", 
                                "bottom_left", "top_right", "up", vertices);
        createOrAddVertexFromNeighbours("bottom_left", "bottom_left", "top_right", 
                                "left", "right", "down", vertices);
        createOrAddVertexFromNeighbours("top_left", "left", "right", 
                                "top_left", "bottom_right", "up", vertices);
        
        return vertices;
    }
    
    private void createOrAddVertexFromNeighbours(String vertexName, String neighbour1TileKey, String neighbour1EdgeKey, 
                                        String neighbour2TileKey, String neighbour2EdgeKey, 
                                        String vertexDirection, Map<String, Vertex> vertices) {
        // Check if either neighbor already has the desired vertex
        Vertex vertex = findVertexFromNeighbour(neighbour1TileKey, neighbour1EdgeKey, vertexDirection);
        
        if (vertex == null) {
            vertex = findVertexFromNeighbour(neighbour2TileKey, neighbour2EdgeKey, vertexDirection);
        }

        // If neither neighbor has the vertex, create a new one
        if (vertex == null) {
            vertex = new Vertex();
        }

        // Ensure the vertex is in the vertices map
        vertices.put(vertexName, vertex);

        // Ensure the vertex has both edges and both edges have the vertex
        addEdgeAndVertex(tile.getEdge(neighbour1TileKey), vertex, vertexDirection);
        addEdgeAndVertex(tile.getEdge(neighbour2TileKey), vertex, vertexDirection);
    }

    private Vertex findVertexFromNeighbour(String neighbourTileKey, String neighbourEdgeKey, String vertexDirection) {
        if (tile.getNeighbours().get(neighbourTileKey) != null) {
            return tile.getNeighbours().get(neighbourTileKey).getEdge(neighbourEdgeKey).getVertices().get(vertexDirection);
        }
        return null;
    }

    private void addEdgeAndVertex(Edge edge, Vertex vertex, String vertexDirection) {
        if (!vertex.getEdges().contains(edge)) {
            vertex.addEdge(edge);
            edge.addVertex(vertex, vertexDirection);
        }
    }
}
