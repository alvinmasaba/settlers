package com.masaba.settlers.model;

import org.junit.jupiter.api.Test;

import com.masaba.settlers.model.tile.Tile;
import com.masaba.settlers.model.tile.Vertex;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;

public class VertexTest {
    Tile[][] board = new Board(19).board;

    @Test
    void testVerticesHaveTwoOrThreeEdges() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Map<String, Vertex> vertices = board[i][j].getVertices();
                for (Vertex vertex : vertices.values()) {
                    int edgeCount = vertex.getEdges().size();
                    assertTrue(edgeCount >= 2 && edgeCount <= 3);
                }
            }
        }
    }

}
