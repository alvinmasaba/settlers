package com.masaba.settlers.factory;

import com.masaba.settlers.model.tile.Tile;

import java.util.*;

public class NeighbourFactory {
    private Tile tile;
    private Tile[][] board;
    private int midRow;
    private String[] directions = { "top_left", "top_right", "right", "bottom_right", "bottom_left", "left" }; // Represents the six possible directions from a tile
    private String[] oppositeDirections = { "bottom_right", "bottom_left", "left", "top_left", "top_right", "right" }; // Each direction has a corresponding opposite direction
    private int[] rowAdjustments = { -1, -1, 0, 1, 1, 0 }; // Define the adjustments that should be made to the row for each direction
    private int[] colAdjustments = { -1, 0, 1, 1, -1, -1 };
    private int[] colAdjustmentsLowerHalf = { 0, 1, 1, 0, -1, -1 }; // For rows greater than midRow, the column adjustments are different


    public NeighbourFactory(Tile tile, Tile[][] board) {
       this.tile = tile;
       this.board = board;
       this.midRow = (board.length % 2 == 0) ? board.length / 2 : (board.length - 1) / 2; // midRow is used to differentiate behaviour above and below the middle row
    }

    // This method finds the neighbours of a specific tile on the board
    public Map<String, Tile> findAndAddNeighbours(int row, int index) {
        Map<String, Tile> neighbours = new HashMap<>();

        for (int i = 0; i < directions.length; i++) {
            // Calculate the row and column of the neighbouring tile in the current direction
            int newRow = row + this.rowAdjustments[i];
            int newCol = (row > this.midRow) ? index + this.colAdjustmentsLowerHalf[i] : index + this.colAdjustments[i];
            addIfNeighborExists(directions[i], oppositeDirections[i], newRow, newCol, neighbours);
        }

        return neighbours;
    }


    private void addIfNeighborExists(String direction, String oppositeDirection, int newRow, int newCol, Map<String, Tile> neighbours) {
        // If the index is valid, attempt to add the neighbouring tile in that direction
        if (isValidIndex(newRow, newCol)) {

            Tile neighborTile = board[newRow][newCol];
            if (neighborTile != null) {
                neighbours.put(direction, neighborTile);
                addTileToNeighbour(neighborTile, oppositeDirection);
            }

        }
    }


    private void addTileToNeighbour(Tile neighbour, String direction) {
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
