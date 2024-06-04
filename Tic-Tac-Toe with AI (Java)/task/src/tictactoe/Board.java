package tictactoe;

import java.util.Arrays;


public class Board {
    private final Cell[][] board;

    public Board() {
        this.board = new Cell[3][3];
        buildBoard();
    }

    public void buildBoard() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = new Cell(State.EMPTY);
            }
        }
    }

    public Cell[][] getBoard() {
        return this.board;
    }

    public void printBoard() {
        System.out.println("---------");
        for (int i = 0; i < this.board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < this.board[i].length; j++) {
                System.out.print(this.board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
}
