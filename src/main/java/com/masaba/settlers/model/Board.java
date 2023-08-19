package com.masaba.settlers.model;

import com.masaba.settlers.model.tile.Tile;
import java.util.*;

public class Board {
    public int size;
    public Tile[][] board;
    public Map<String, Integer> numbers;
    public Map<String, Integer> resourceTiles;
    public Map<String, Integer> ports;
    // public Map<String, Integer> resourceCards;


    public Board(int size) {
        this.size = size;
        this.board = createBoard();
        this.numbers = createTokens();
        this.resourceTiles = createResourceTiles();
        // this.resourceCards = new HashMap<>(Map.of("clay", 19, "wheat", 19, "wood", 19, "wool", 19, "ore", 19));
        this.ports = new HashMap<>(Map.of("clay", 1, "wheat", 1, "wood", 1, "wool", 1, "ore", 1, "generic", 4));
    }

    public Tile getTileFromBoard(int row, int col) {
        if (row >= 0 && row < board.length && col >= 0 && col < board[row].length) {
            return board[row][col];
        } else {
            return null;
        }
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

    private Map<String, Integer> createTokens() {   
        Map<String, Integer> tokens = new HashMap<>(Map.ofEntries(
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
        if (this.size == 30) {
            // Code for adding the extra number tokens to the map of tokens
        }

        return tokens;
    }

    private Map<String, Integer> createResourceTiles() {
        if (this.size < 30) {
            return new HashMap<>(Map.of("clay", 3, "wheat", 4, "wood", 4, "wool", 4, "ore", 3, "desert", 1));
        } else {
            return new HashMap<>(Map.of("clay", 5, "wheat", 6, "wood", 6, "wool", 6, "ore", 5, "desert", 2));
        }
    }

    private Map<String, Integer> createResourceCards() {
        if (this.size < 30) {
            Map<String, Integer> resources = new HashMap<>(Map.of("clay", 19, "wheat", 19, "wood", 19, "wool", 19, "ore", 19));
        } else {
            Map<String, Integer> resources = new HashMap<>(Map.of("clay", 24, "wheat", 24, "wood", 24, "wool", 24, "ore", 24));
        }

        Map<String, Integer> resourceCards = new HashMap<>();
        
        for (var resource : resources.entrySet()) {
            
        }
    }
}
