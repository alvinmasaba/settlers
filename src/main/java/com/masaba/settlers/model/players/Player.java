package com.masaba.settlers.model.players;

import java.util.*;

import com.masaba.settlers.model.Road;
import com.masaba.settlers.model.tile.*;
import com.masaba.settlers.model.buildings.Building;
import com.masaba.settlers.model.cards.DevelopmentCard;

public class Player {
    private String name;
    private String colour;
    private Map<String, Integer> resources;
    private List<DevelopmentCard> developmentCards;
    private List<Road> roads;
    private List<Building> buildings;
    private int score;


    public Player(String name, String colour) {
        this.name = name;
        this.colour = colour;
        this.resources = Map.of("clay", 0, "ore", 0, "wheat", 0, "wood", 0, "wool", 0);
        this.developmentCards = new ArrayList<>();
        this.roads = new ArrayList<>();
        this.buildings = new ArrayList<>();
        this.score = 0;
    }


    public void addBuilding(Building building) {
        buildings.add(building);
    }


    public List<Building> getBuildings() {
        return this.buildings;
    }

    
    public void addRoad(Edge edge) {
        int clay = this.resources.get("clay");
        int wood = this.resources.get("wood");


        // The cost of a road is 1 clay and 1 wood.
        if (clay > 0 && wood > 0) {
            useResource("clay", 1);
            useResource("wood", 1);
            buildRoad(edge);
        }
    }


    public List<Road> getRoads() {
        return this.roads;
    }


    private void buildRoad(Edge edge) {
        Road road = new Road(this, edge);
        this.roads.add(road);
    }


    private void useResource(String resource, int num) {
        // Replace the current value of resource with the updated value
        resources.put(resource, resources.get(resource) - num);
    }
}
