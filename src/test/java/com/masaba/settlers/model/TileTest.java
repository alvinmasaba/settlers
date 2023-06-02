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
            }
        }
        
        Tile upperRowEdgeTile = board[0][0];
        Tile inlandTile = board[1][1];
        Map<String, Tile> upperRowEdgeNeighbours = upperRowEdgeTile.getNeighbours();
        Map<String, Tile> inlandNeighbours = inlandTile.getNeighbours();

        assertEquals(board[0][0], inlandNeighbours.get("top_left"));
        assertEquals(board[0][1], inlandNeighbours.get("top_right"));
        assertEquals(board[1][2], inlandNeighbours.get("right"));
        assertEquals(board[2][2], inlandNeighbours.get("bottom_right"));
        assertEquals(board[2][1], inlandNeighbours.get("bottom_left"));
        assertEquals(board[1][0], inlandNeighbours.get("left"));

        assertNull(upperRowEdgeNeighbours.get("top_left"));
        assertNull(upperRowEdgeNeighbours.get("top_right"));
        assertEquals(board[0][1], upperRowEdgeNeighbours.get("right"));
        assertEquals(board[1][1], upperRowEdgeNeighbours.get("bottom_right"));
        assertEquals(board[1][0], upperRowEdgeNeighbours.get("bottom_left"));
        assertNull(upperRowEdgeNeighbours.get("left"));
    }
}
