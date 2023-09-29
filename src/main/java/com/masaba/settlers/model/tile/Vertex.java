package com.masaba.settlers.model.tile;

import java.util.ArrayList;
import java.util.List;

import com.masaba.settlers.model.buildings.Building;
import com.masaba.settlers.model.players.Player;
import com.masaba.settlers.model.Road;

public class Vertex {
    private List<Tile> neighbours;
    private List<Edge> edges;
    private Building building;

    public Vertex() {
        this.neighbours = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.building = null;
    }

    public List<Tile> getNeighbours() {
        return neighbours;
    }

    public void addNeighbour(Tile tile) {
        this.neighbours.add(tile);
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }

    public List<Edge> getEdges() {
        return this.edges;
    }

    public boolean hasTwoConsecutiveRoads(Player player) {
        for (Edge edge : this.getEdges()) {
            // If the edge has a road belonging to the player
            if (edge.hasRoadOwnedBy(player)) {
                // Get the opposite vertex and check its edges for roads owned by player
                Vertex vertex2 = edge.getOtherVertex(this);
                for (Edge adjEdge : vertex2.getEdges()) {
                    // If the adjacent edge is not the same as the previous edge and has a road owned by the player
                    if (!adjEdge.equals(edge) && adjEdge.hasRoadOwnedBy(player)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
}
