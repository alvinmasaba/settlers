package com.masaba.settlers.model.players;

import java.util.*;

import com.masaba.settlers.model.Road;
import com.masaba.settlers.model.tile.*;
import com.masaba.settlers.model.buildings.*;
import com.masaba.settlers.model.cards.DevelopmentCard;
import com.masaba.settlers.model.cards.developmentCards.VictoryPointCard;

public class Player {
    private String name;
    private String colour;
    private Map<String, Integer> resources;
    private List<DevelopmentCard> developmentCards;
    private List<Road> roads;
    private List<Building> buildings;
    private Integer score;
    private Integer unusedCities;
    private Integer unusedSettlements;
    private Integer unusedRoads;

    public Player(String name, String colour) {
        this.name = name;
        this.colour = colour;
        this.resources = new HashMap<>(Map.of("clay", 0, "ore", 0, "wheat", 0, "wood", 0, "wool", 0));
        this.developmentCards = new ArrayList<>();
        this.roads = new ArrayList<>();
        this.buildings = new ArrayList<>();
        this.score = calculateScore();
        this.unusedCities = 4;
        this.unusedSettlements = 5;
        this.unusedRoads = 15;
    }

    public List<Building> getBuildings() {
        return this.buildings;
    }

    public List<Road> getRoads() {
        return this.roads;
    }

    public Map<String, Integer> getResources() {
        return this.resources;
    }

    public List<DevelopmentCard> getDevelopmentCards() {
        return this.developmentCards;
    }

    public void addResources(Map<String, Integer> addedResources) {
        for (var resource : addedResources.entrySet()) {
            this.resources.put(resource.getKey(), (this.resources.get(resource.getKey()) + resource.getValue()));
        }
    }
    
    public void addSettlement(Vertex vertex) {
        Map<String, Integer> requiredResources = Map.of("clay", 1, "wheat", 1, "wood", 1, "wool", 1);

        if (canBuildBuilding(vertex, requiredResources)) {
            buildBuilding("settlement", vertex, requiredResources);
            this.score++;
            this.unusedSettlements--;
        }
    }

    public void addCity(Vertex vertex) {
        Map<String, Integer> requiredResources = Map.of("wheat", 2, "ore", 3);

        if (haveSufficientResources(requiredResources)) {
            buildBuilding("city", vertex, requiredResources);
            this.score++;
            this.unusedCities--;
            this.unusedSettlements++;
        }
    }

    public void addRoad(Edge edge) {
        Map<String, Integer> requiredResources = Map.of("clay", 1, "wood", 1);

        if (edge.getRoad() == null && haveSufficientResources(requiredResources)) {
            useResources(requiredResources);
            Road road = new Road(this, edge);
            edge.setRoad(road);
            this.roads.add(road);
            this.unusedRoads--;
        }
    }

    
    // HELPER FUNCTIONS


    private Integer calculateScore() {
        Integer score = 0;
        for (Building building : this.buildings) {
            score += building.getValue();
        }

        for (DevelopmentCard card : this.developmentCards) {
            if (card.getType().equals("victory")){
                score++;
            }
        }

        return score;
    }

    private Boolean canBuildBuilding(Vertex vertex, Map<String, Integer> requiredResources) {
        return (vertex.hasTwoConsecutiveRoads(this) && haveSufficientResources(requiredResources));
    }

    private void buildBuilding(String type, Vertex vertex, Map<String, Integer> requiredResources) {
        Building building = null;

        if (type.equals("settlement") && vertex.getBuilding() == null){
            building = new Settlement(this, vertex);
        } else if (type.equals("city") && vertex.getBuilding() instanceof Settlement
                    && vertex.getBuilding().getOwner().equals(this)) {
            building = new City(this, vertex);
        }

        if (building != null) {
            finalizeBuildingConstruction(building, vertex, requiredResources);
        }
    }

    private void finalizeBuildingConstruction(Building building, Vertex vertex, Map<String, Integer> requiredResources) {
        useResources(requiredResources);
        vertex.setBuilding(building);
        buildings.add(building);
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

    private void useResources(Map<String, Integer> usedResources) {
        // Replace the current values of the used resources with their updated values
        for (var resource : usedResources.entrySet()) {
            this.resources.put(resource.getKey(), this.resources.get(resource.getKey()) - resource.getValue());
        }
    }
}
