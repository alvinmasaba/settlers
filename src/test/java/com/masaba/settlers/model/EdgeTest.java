package com.masaba.settlers.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;


public class EdgeTest {
    Tile[][] board = new Board().board;

    @Test
    void testEdgesMappedToTwoVertices() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Map<String, Edge> edges = board[i][j].getEdges();
                for (var entry : edges.entrySet()) {
                    Edge edge = entry.getValue();
                    assertEquals(2, edge.getVertices().size());
                }
            }
        }
    }

    @Test
    void testEdgesHaveUpandDownVertices() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Map<String, Edge> edges = board[i][j].getEdges();
                // System.out.println("Tile [" + i + "]" + "[" + j + "] Vertices: ");               
                for (var entry : edges.entrySet()) {
                    //System.out.println(entry.getKey() + ": " + entry.getValue().getVertices());
                    assertNotNull(entry.getValue().getVertex("up"));
                    assertNotNull(entry.getValue().getVertex("down"));
                }
            }
        }
    }
}
