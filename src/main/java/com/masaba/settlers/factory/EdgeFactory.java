package com.masaba.settlers.factory;

import java.util.*;
import com.masaba.settlers.model.*;
import com.masaba.settlers.model.tile.Edge;
import com.masaba.settlers.model.tile.Tile;

public class EdgeFactory {
    private Map<String, Edge> edges = new HashMap<>();
    private Tile tile;

    public EdgeFactory(Tile tile) {
        this.tile = tile;
    }

    public Edge getOrCreateEdge(Tile tile, String edgeKey, String neighbourKey) {
        Edge edge;
        
        if (tile.getNeighbours().get(neighbourKey) != null) {
            edge = tile.getNeighbours().get(neighbourKey).getEdge(edgeKey);
        } else {
            edge = new Edge();
            edges.put(edgeKey, edge);
        }
        
        edge.addNeighbour(tile);
        return edge;
    }


    public Map<String, Edge> findOrCreateEdges() {
        Map<String, Edge> edges = new HashMap<>();
    
        edges.put("top_left", getOrCreateEdge(this.tile, "bottom_right", "top_left"));
        edges.put("top_right", getOrCreateEdge(this.tile, "bottom_left", "top_right"));
        edges.put("right", getOrCreateEdge(this.tile, "left", "right"));
        edges.put("bottom_right", getOrCreateEdge(this.tile, "top_left", "bottom_right"));
        edges.put("bottom_left", getOrCreateEdge(this.tile, "top_right", "bottom_left"));
        edges.put("left", getOrCreateEdge(this.tile, "right", "left"));
    
        return edges;
    }
}
