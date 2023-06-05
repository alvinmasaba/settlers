package com.masaba.settlers.model;

public class Board {
    public Tile[][] board;

    public Board() {
        this.board = createBoard();
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
                board[i][j] = new Tile(i, j, board);
            }
        }

        return board;
    }
}
