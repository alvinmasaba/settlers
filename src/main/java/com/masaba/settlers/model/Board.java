package com.masaba.settlers.model;

import com.masaba.settlers.model.tile.Tile;
import java.util.*;

public class Board {
    public Tile[][] board;
    public Map<String, Integer> numbers;
    public Map<String, Integer> resourceTiles;
    public Map<String, Integer> ports;
    // public Map<String, Integer> resourceCards;


    public Board() {
        this.board = createBoard();
        this.numbers = new HashMap<>(Map.ofEntries(
                                                    Map.entry("A", 5),
                                                    Map.entry("B", 2),
                                                    Map.entry("C", 6),
                                                    Map.entry("D", 3),
                                                    Map.entry("E", 8),
                                                    Map.entry("F", 10),
                                                    Map.entry("G", 9),
                                                    Map.entry("H", 12),
                                                    Map.entry("I", 11),
                                                    Map.entry("J", 4),
                                                    Map.entry("K", 8),
                                                    Map.entry("L", 10),
                                                    Map.entry("M", 9),
                                                    Map.entry("N", 4),
                                                    Map.entry("O", 5),
                                                    Map.entry("P", 6),
                                                    Map.entry("Q", 3),
                                                    Map.entry("R", 11)
                                                ));

        this.resourceTiles = new HashMap<>(Map.of("clay", 3, "wheat", 4, "wood", 4, "wool", 4, "ore", 3));
        // this.resourceCards = new HashMap<>(Map.of("clay", 19, "wheat", 19, "wood", 19, "wool", 19, "ore", 19));
        this.ports = new HashMap<>(Map.of("clay", 1, "wheat", 1, "wood", 1, "wool", 1, "ore", 1, "generic", 4));
    }

    
    private Tile[][] createBoard() {
        Tile[][] board = new Tile[5][];

        board[0] = new Tile[3];
        board[1] = new Tile[4];
        board[2] = new Tile[5];
        board[3] = new Tile[4];
        board[4] = new Tile[3];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Tile(i, j, "clay", board);
            }
        }

        return board;
    }


    public Tile getTileFromBoard(int row, int col) {
        if (row >= 0 && row < board.length && col >= 0 && col < board[row].length) {
            return board[row][col];
        } else {
            return null;
        }
    }
}
