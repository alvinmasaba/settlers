package com.masaba.settlers.model.buildings;

import com.masaba.settlers.model.players.Player;
import com.masaba.settlers.model.tile.*;

public class City extends Building {
    protected Integer value = 2;
    
    public City(Player owner, Vertex location) {
        super(owner, location);
    }
    
}
