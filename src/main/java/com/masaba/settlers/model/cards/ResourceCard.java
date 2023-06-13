package com.masaba.settlers.model.cards;

import com.masaba.settlers.model.players.Player;

public abstract class ResourceCard extends Card {
    private Player owner;
    private String type;


    public ResourceCard(Player owner, String type) {
        super(owner, type);
    }
}
