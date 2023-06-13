package com.masaba.settlers.model.cards;

import com.masaba.settlers.model.players.Player;

public abstract class Card {
    private Player owner;
    private String type;


    public Card(Player owner, String type) {
        this.owner = owner;
        this.type = type;
    }


    // method that will be overridden by subclasses to use the card
    public abstract void use();


    public Player getOwner() {
        return this.owner;
    }


    public String getType() {
        return this.type;
    }
}
