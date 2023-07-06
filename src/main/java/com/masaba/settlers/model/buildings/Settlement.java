package com.masaba.settlers.model.buildings;

import com.masaba.settlers.model.players.Player;
import com.masaba.settlers.model.tile.*;

public class Settlement extends Building {
    protected Integer value = 1;

    public Settlement(Player owner, Vertex location) {
        super(owner, location);
    }
}
