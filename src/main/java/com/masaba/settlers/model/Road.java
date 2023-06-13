package com.masaba.settlers.model;

import com.masaba.settlers.model.players.Player;
import com.masaba.settlers.model.tile.Edge;

public class Road {
    private Player owner;
    private Edge edge;


    public Road(Player owner, Edge edge) {
        this.owner = owner;
        this.edge = edge;
    }


    public Player getOwner() {
        return this.owner;
    }


    public Edge getEdge() {
        return this.edge;
    }
}
