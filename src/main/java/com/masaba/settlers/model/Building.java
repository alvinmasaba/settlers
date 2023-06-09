package com.masaba.settlers.model;

public class Building {
    private Player owner;

    public Building(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }
}
