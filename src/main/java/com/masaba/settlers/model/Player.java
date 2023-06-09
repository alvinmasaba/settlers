package com.masaba.settlers.model;

import java.util.*;

import com.masaba.settlers.model.buildings.Building;
import com.masaba.settlers.model.cards.DevelopmentCard;

public class Player {
    private String name;
    private Map<String, Integer> resources;
    private List<DevelopmentCard> developmentCards;
    private List<Road> roads;
    private List<Building> buildings;
    private int victoryPoints;

    public Player(String name) {
        this.name = name;
        this.resources = new HashMap<>();
        this.developmentCards = new ArrayList<>();
        this.roads = new ArrayList<>();
        this.buildings = new ArrayList<>();
        this.victoryPoints = 0;
    }

    // basic getters and setters here

    // other potential methods to handle player actions
}
