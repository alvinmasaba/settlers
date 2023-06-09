package com.masaba.settlers.model.buildings;

import com.masaba.settlers.model.Player;

public class Building {
    private Player owner;

    public Building(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }
}
