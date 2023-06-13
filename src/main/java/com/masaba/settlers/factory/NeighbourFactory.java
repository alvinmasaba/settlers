package com.masaba.settlers.factory;

import com.masaba.settlers.model.tile.Tile;

import java.util.*;

public class NeighbourFactory {
    private Tile tile;
    private Tile[][] board;

    public NeighbourFactory(Tile tile, Tile[][] board) {
       this.tile = tile;
       this.board = board;
    }

    // This method finds the neighbours of a specific tile on the board
    public Map<String, Tile> findAndAddNeighbours(int row, int index) {
        Map<String, Tile> neighbours = new HashMap<>();

        // midRow is used to differentiate behaviour above and below the middle row
        int midRow = (board.length % 2 == 0) ? board.length / 2 : (board.length - 1) / 2;

        // Define arrays to represent the six possible directions from a tile
        String[] directions = { "top_left", "top_right", "right", "bottom_right", "bottom_left", "left" };

        // Each direction has a corresponding opposite direction
        String[] oppositeDirections = { "bottom_right", "bottom_left", "left", "top_left", "top_right", "right" };

        // Define the adjustments that should be made to the row and column for each direction
        int[] rowAdjustments = { -1, -1, 0, 1, 1, 0 };
        int[] colAdjustments = { -1, 0, 1, 1, -1, -1 };

        // For rows greater than midRow, the column adjustments are different
        int[] colAdjustmentsLowerHalf = { 0, 1, 1, 0, -1, -1 };

        // Loop through all directions
        for (int i = 0; i < directions.length; i++) {
            // Calculate the row and column of the neighbouring tile in the current direction
            int newRow = row + rowAdjustments[i];
            int newCol = (row > midRow) ? index + colAdjustmentsLowerHalf[i] : index + colAdjustments[i];

            // Check that the calculated row and column are valid indexes in the board
            if (isValidIndex(newRow, newCol)) {
                // If they are, attempt to add the neighbouring tile in that direction
                addIfNeighborExists(directions[i], oppositeDirections[i], newRow, newCol, neighbours);
            }
        }

        return neighbours;
    }

    // This method checks if a neighbouring tile exists at the given row and column, 
    // and if so, adds it to the neighbours map in the given direction
    private void addIfNeighborExists(String direction, String oppositeDirection, int newRow, int newCol, Map<String, Tile> neighbours) {
        Tile neighborTile = board[newRow][newCol];
        if (neighborTile != null) {
            neighbours.put(direction, neighborTile);
            // Also adds this tile as a neighbour to the neighbour tile in the opposite direction
            addSelfToNeighbour(neighborTile, oppositeDirection);
        }
    }

    // This method adds this tile as a neighbour to the given tile in the given direction
    private void addSelfToNeighbour(Tile neighbour, String direction) {
        if (neighbour != null) {
            neighbour.addNeighbour(direction, tile);
        }
    }
    
    
    private Boolean isValidIndex(int row, int col) {
        if (row >= 0 && row < board.length && col >= 0 && col < this.board[row].length) {
            return true;
        }

        return false;
    }
}
