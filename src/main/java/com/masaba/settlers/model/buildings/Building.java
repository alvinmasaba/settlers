package com.masaba.settlers.model.buildings;

import com.masaba.settlers.model.players.Player;
import com.masaba.settlers.model.tile.*;

public abstract class Building {
    protected Player owner;
    protected Vertex location;

    public Building(Player owner, Vertex location) {
        this.owner = owner;
        this.location = location;
    }

    public Player getOwner() {
        return owner;
    }
}
