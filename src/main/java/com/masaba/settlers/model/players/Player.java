package com.masaba.settlers.model.players;

import java.util.*;

import com.masaba.settlers.model.Road;
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


    public void addBuilding(Building building) {
        buildings.add(building);
    }


    public List<Building> getBuildings() {
        return this.buildings;
    }

    
    public void addRoad(Road road) {
        this.roads.add(road);
    }


    public List<Road> getRoads() {
        return this.roads;
    }
}
