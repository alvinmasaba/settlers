package com.masaba.settlers.model.buildings;

import com.masaba.settlers.model.players.Player;
import com.masaba.settlers.model.tile.*;

public abstract class Building {
    protected Player owner;
    protected Vertex location;
    protected Integer value;

    public Building(Player owner, Vertex location) {
        this.owner = owner;
        this.location = location;
        this.value = null;
    }

    public Player getOwner() {
        return owner;
    }

    public Integer getValue() {
        return value;
    }
}
