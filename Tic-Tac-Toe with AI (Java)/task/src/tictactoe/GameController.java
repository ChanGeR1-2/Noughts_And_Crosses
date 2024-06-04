package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class GameController {
    private static final Board board = new Board();
    private static final UserInterface ui = new UserInterface(new Scanner(System.in));
    private static final Player[] players = new Player[2];

    public static void run() {
        while (true) {
            String input = ui.startInput();
            if (input.equals("exit")) {
                break;
            }
            setPlayers(input);
            State winningSymbol = makeMoves();
            board.printBoard();
            if (checkDraw()) {
                System.out.println("Draw");
            } else {
                System.out.printf("%s wins\n", winningSymbol);
            }
            board.buildBoard();
            System.out.println();
        }

    }

    private static void setPlayers(String input) {
        String[] parts = input.replaceAll("start ", "").split(" ");
        String player1 = parts[0];
        String player2 = parts[1];
        switch (player1) {
            case "user" -> players[0] = new User(State.X, ui);
            case "easy" -> players[0] = new ComputerEasy(State.X);
            case "medium" -> players[0] = new ComputerMedium(State.X);
            case "hard" -> players[0] = new ComputerHard(State.X);
        }
        switch (player2) {
            case "user" -> players[1] = new User(State.O, ui);
            case "easy" -> players[1] = new ComputerEasy(State.O);
            case "medium" -> players[1] = new ComputerMedium(State.O);
            case "hard" -> players[1] = new ComputerHard(State.O);
        }
    }

    private static State makeMoves() {
        int turn = 0;
        Integer[] lastMove;
        do {
            board.printBoard();
            lastMove = players[turn].move(board.getBoard());
            if (turn == 1) {
                turn = 0;
            } else {
                turn++;
            }
        } while (!checkWin(lastMove) && !checkDraw());
        return board.getBoard()[lastMove[0]][lastMove[1]].getState();
    }

    public static boolean checkDraw() {
        for (int j = 0; j < board.getBoard().length; j++) {
            for (int i = 0; i < board.getBoard()[j].length; i++) {
                if (board.getBoard()[i][j].getState() == State.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkWin(Integer[] lastMove) {
        State symbol = board.getBoard()[lastMove[0]][lastMove[1]].getState();
        return checkHorizontal(symbol, board.getBoard()) || checkVertical(symbol, board.getBoard()) || checkDiagonal(symbol, board.getBoard()) || checkAntiDiagonal(symbol, board.getBoard());
    }

    public static boolean checkWin(State symbol, Cell[][] board) {
        return checkHorizontal(symbol, board) || checkVertical(symbol, board) || checkDiagonal(symbol, board) || checkAntiDiagonal(symbol, board);
    }

    private static boolean checkHorizontal(State symbol, Cell[][] board) {
        for (int i = 0; i < board.length; i++) {
            boolean temp = true;
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getState() != symbol) {
                    temp = false;
                }
            }
            if (temp) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkVertical(State symbol, Cell[][] board) {
        for (int j = 0; j < board.length; j++) {
            boolean temp = true;
            for (int i = 0; i < board[j].length; i++) {
                if (board[i][j].getState() != symbol) {
                    temp = false;
                }
            }
            if (temp) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkDiagonal(State symbol, Cell[][] board) {
        int count = 0;
        for (int i = 0; i <= 2; i++) {
            boolean temp = board[i][i].getState() == symbol;
            if (temp) {
                count++;
            }
        }
        return count == 3;
    }

    private static boolean checkAntiDiagonal(State symbol, Cell[][] board) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            boolean temp = board[i + 1][1 - i].getState() == symbol;
            if (temp) {
                count++;
            }
        }
        return count == 3;
    }

}
