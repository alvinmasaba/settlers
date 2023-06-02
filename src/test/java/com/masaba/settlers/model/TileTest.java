package com.masaba.settlers.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;

class TileTest {
    @Test
    void testGetNeighbours() {
        Tile[][] board = new Tile[5][];

        board[0] = new Tile[3];
        board[1] = new Tile[4];
        board[2] = new Tile[5];
        board[3] = new Tile[4];
        board[4] = new Tile[3];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Tile(i, j, board);
                System.out.println("Row: " + i + "Index: " + j);
            }
        }
        
        Map<String, Tile> neighbours = board[1][1].getNeighbours();

        assertEquals(board[0][0], neighbours.get("top_left"));
        assertEquals(board[0][1], neighbours.get("top_right"));
        assertEquals(board[1][2], neighbours.get("right"));
        assertEquals(board[2][2], neighbours.get("bottom_right"));
        assertEquals(board[2][1], neighbours.get("bottom_left"));
        assertEquals(board[1][0], neighbours.get("left"));

    }
}
