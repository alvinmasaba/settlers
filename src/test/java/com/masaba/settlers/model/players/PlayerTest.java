package com.masaba.settlers.model.players;

import com.masaba.settlers.model.Road;
import com.masaba.settlers.model.buildings.*;
import com.masaba.settlers.model.tile.*;
import java.util.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("TestPlayer", "Red");
    }

    @Test
    void testAddSettlement() {
        Vertex v = new Vertex(); // Create a new vertex instance

        player.addSettlement(v);
        assertEquals(0, player.getBuildings().size()); // The player doesn't have enough resources, so nothing should be built

        player.addResources(Map.of("clay", 1, "wheat", 1, "wood", 1, "wool", 1));

        player.addSettlement(v);
        assertEquals(0, player.getBuildings().size()); // The vertex is not connected to 2 consecutive roads owned by the player 

        Edge e = new Edge();
        Edge e2 = new Edge();
        Vertex v2 = new Vertex();
        Road r1 = new Road(player, e);
        Road r2 = new Road(player, e2);
        e.setRoad(r1);
        e2.setRoad(r2);
        e.addVertex(v, "up");
        e.addVertex(v2, "down");
        e2.addVertex(v2, "up");
        v.addEdge(e);
        v2.addEdge(e);
        v2.addEdge(e2);

        player.addSettlement(v);
        assertEquals(1, player.getBuildings().size()); // Now the player should have one settlement
        assertTrue(player.getBuildings().get(0) instanceof Settlement); // Make sure the built building is a settlement
    }

    @Test
    void testAddCity() {
        Vertex v = new Vertex(); // Create a new vertex instance
        Vertex emptyVertex = new Vertex(); // Create a new vertex instance
        Settlement s = new Settlement(player, v); // Create a new settlement
        v.setBuilding(s); //  Place new settlement on vertex

        player.addCity(v);
        assertEquals(0, player.getBuildings().size()); // The player doesn't have enough resources, so nothing should be built

        player.addResources(Map.of("wheat", 2, "ore", 3));

        player.addCity(v);
        assertEquals(1, player.getBuildings().size()); // Now the player should have one city
        assertTrue(player.getBuildings().get(0) instanceof City); // Make sure the built building is a city

        player.addCity(emptyVertex);
        assertNull(emptyVertex.getBuilding()); 
    }

    @Test
    void testAddRoad() {
        Edge e = new Edge(); // Create a new edge instance

        player.addRoad(e);
        assertEquals(0, player.getRoads().size()); // The player doesn't have enough resources, so nothing should be built

        player.addResources(Map.of("clay", 1, "wood", 1));

        player.addRoad(e);
        assertEquals(1, player.getRoads().size()); // Now the player should have one road
        for (var road : player.getRoads()) {
            assertTrue(road instanceof Road);
        }
    }
}
