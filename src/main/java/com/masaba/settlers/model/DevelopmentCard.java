package com.masaba.settlers.model;

public abstract class DevelopmentCard {
    private Player owner;

    public DevelopmentCard(Player owner) {
        this.owner = owner;
    }

    // method that will be overridden by subclasses to use the card
    public abstract void use();

    public Player getOwner() {
        return owner;
    }
}