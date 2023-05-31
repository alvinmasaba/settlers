package com.masaba.settlers.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;

class TileTest {
    @Test
    void testFindNeighbours() {
        Tile[][] board = new Tile[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new Tile(i, j, board); 
            }
        }

        Tile tile = new Tile(1, 1, board);
        Map<String, Tile> neighbours = tile.getNeighbours();

        assertEquals(board[0][0], neighbours.get("top_left"));
        assertEquals(board[0][1], neighbours.get("top_right"));
        assertEquals(board[1][2], neighbours.get("right"));
        assertEquals(board[2][2], neighbours.get("bottom_right"));
        assertEquals(board[2][1], neighbours.get("bottom_left"));
        assertEquals(board[1][0], neighbours.get("left"));

    }
}
