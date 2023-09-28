package com.masaba.settlers.model;

import org.junit.jupiter.api.Test;

import com.masaba.settlers.model.tile.Edge;
import com.masaba.settlers.model.tile.Tile;
import com.masaba.settlers.model.tile.Vertex;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;

class TileTest {
    Tile[][] board = new Board(19).board;

    @Test
    void testGetNeighbours_UpperRowEdgeTile() {      
        Tile upperRowEdgeTile = board[0][0];

        Map<String, Tile> upperRowEdgeNeighbours = upperRowEdgeTile.getNeighbours();

        assertNull(upperRowEdgeNeighbours.get("top_left"));
        assertNull(upperRowEdgeNeighbours.get("top_right"));
        assertEquals(board[0][1], upperRowEdgeNeighbours.get("right"));
        assertEquals(board[1][1], upperRowEdgeNeighbours.get("bottom_right"));
        assertEquals(board[1][0], upperRowEdgeNeighbours.get("bottom_left"));
        assertNull(upperRowEdgeNeighbours.get("left"));
    }

    @Test
    void testGetNeighbours_InlandTile() {      
        Tile inlandTile = board[1][1];

        Map<String, Tile> inlandNeighbours = inlandTile.getNeighbours();

        assertEquals(board[0][0], inlandNeighbours.get("top_left"));
        assertEquals(board[0][1], inlandNeighbours.get("top_right"));
        assertEquals(board[1][2], inlandNeighbours.get("right"));
        assertEquals(board[2][2], inlandNeighbours.get("bottom_right"));
        assertEquals(board[2][1], inlandNeighbours.get("bottom_left"));
        assertEquals(board[1][0], inlandNeighbours.get("left"));
    }

    @Test
    void testGetNeighbours_InlandTile2() {        
        Tile inlandTile2 = board[3][2];

        Map<String, Tile> inlandNeighbours2 = inlandTile2.getNeighbours();

        assertEquals(board[2][2], inlandNeighbours2.get("top_left"));
        assertEquals(board[2][3], inlandNeighbours2.get("top_right"));
        assertEquals(board[3][3], inlandNeighbours2.get("right"));
        assertEquals(board[4][2], inlandNeighbours2.get("bottom_right"));
        assertEquals(board[4][1], inlandNeighbours2.get("bottom_left"));
        assertEquals(board[3][1], inlandNeighbours2.get("left"));
    }

    @Test
    void testGetNeighbours_LowerRowEdgeTile() {        
        Tile lowerRowEdgeTile = board[4][2];

        Map<String, Tile> lowerRowEdgeNeighbours = lowerRowEdgeTile.getNeighbours();

        assertEquals(board[3][2], lowerRowEdgeNeighbours.get("top_left"));
        assertEquals(board[3][3], lowerRowEdgeNeighbours.get("top_right"));
        assertNull(lowerRowEdgeNeighbours.get("right"));
        assertNull(lowerRowEdgeNeighbours.get("bottom_right"));
        assertNull(lowerRowEdgeNeighbours.get("bottom_left"));
        assertEquals(board[4][1], lowerRowEdgeNeighbours.get("left"));
    }

    @Test
    void testGetNeighbours_Symmetry() {
        Tile tile1 = board[1][1];
        Tile tile2 = board[2][1];
    
        Map<String, Tile> neighbours1 = tile1.getNeighbours();
        Map<String, Tile> neighbours2 = tile2.getNeighbours();
    
        // if tile2 is a bottom_right neighbour of tile1, then tile1 should be a top_left neighbour of tile2
        assertEquals(tile2, neighbours1.get("bottom_left"));
        assertEquals(tile1, neighbours2.get("top_right"));
    }

    @Test
    void testEdgeCount() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Map<String, Edge> edges = board[i][j].getEdges();
                assertEquals(6, edges.size());
            }
        }
    }

    @Test
    void testVertexCount() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Map<String, Vertex> vertices = board[i][j].getVertices();
                assertEquals(6, vertices.size());
            }
        }
    }
}