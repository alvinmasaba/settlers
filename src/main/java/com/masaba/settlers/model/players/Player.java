package com.masaba.settlers.model.players;

import java.util.*;

import com.masaba.settlers.model.Road;
import com.masaba.settlers.model.tile.*;
import com.masaba.settlers.model.buildings.*;
import com.masaba.settlers.model.cards.DevelopmentCard;

public class Player {
    private String name;
    private String colour;
    private Map<String, Integer> resources;
    private List<DevelopmentCard> developmentCards;
    private List<Road> roads;
    private List<Building> buildings;
    private Integer score;


    public Player(String name, String colour) {
        this.name = name;
        this.colour = colour;
        this.resources = Map.of("clay", 0, "ore", 0, "wheat", 0, "wood", 0, "wool", 0);
        this.developmentCards = new ArrayList<>();
        this.roads = new ArrayList<>();
        this.buildings = new ArrayList<>();
        this.score = 0;
    }


    public List<Building> getBuildings() {
        return this.buildings;
    }


    public List<Road> getRoads() {
        return this.roads;
    }

    
    public void addSettlement(Vertex vertex) {
        Map<String, Integer> requiredResources = Map.of("clay", 1, "wheat", 1, "wood", 1, "wool", 1);

        if (haveSufficientResources(requiredResources)) {
            useResources(requiredResources);
            buildBuilding("settlement", vertex);
        }
    }


    public void addCity(Vertex vertex) {
        Map<String, Integer> requiredResources = Map.of("wheat", 2, "ore", 3);

        if (haveSufficientResources(requiredResources)) {
            useResources(requiredResources);
            buildBuilding("city", vertex);
        }
    }


    public void addRoad(Edge edge) {
        Map<String, Integer> requiredResources = Map.of("clay", 1, "wood", 1);

        if (haveSufficientResources(requiredResources)) {
            useResources(requiredResources);
            buildRoad(edge);
        }
    }


    private void buildRoad(Edge edge) {
        Road road = new Road(this, edge);
        this.roads.add(road);
    }


    private void buildBuilding(String type, Vertex vertex) {
        if (type == "settlement") {
            Settlement settlement = new Settlement(this, vertex);
            buildings.add(settlement);
        } else {
            City city = new City(this, vertex);
            buildings.add(city);
        }
    }


    private void useResources(Map<String, Integer> usedResources) {
        // Replace the current values of the used resources with their updated values
        for (var resource : usedResources.entrySet()) {
            this.resources.put(resource.getKey(), this.resources.get(resource.getKey()) - resource.getValue());
        }
    }

    
    private Boolean haveSufficientResources(Map<String, Integer> requiredResources) {
        // Return false if number of owned resources is less than required
        for (var resource : requiredResources.entrySet()) {
            if (this.resources.get(resource.getKey()) < resource.getValue()) {
                return false;
            }
        }

        return true;
    }
}
