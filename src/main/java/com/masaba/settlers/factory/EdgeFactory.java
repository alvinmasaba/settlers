package com.masaba.settlers.factory;

import java.util.*;
import com.masaba.settlers.model.*;

public class EdgeFactory {
    private Map<String, Edge> edges = new HashMap<>();

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
}
