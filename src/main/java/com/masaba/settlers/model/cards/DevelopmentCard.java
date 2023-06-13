package com.masaba.settlers.model.cards;

import com.masaba.settlers.model.players.Player;

public abstract class DevelopmentCard extends Card {
    private Player owner;
    private String type;


    public DevelopmentCard(Player owner, String type) {
        super(owner, type);
    }
}