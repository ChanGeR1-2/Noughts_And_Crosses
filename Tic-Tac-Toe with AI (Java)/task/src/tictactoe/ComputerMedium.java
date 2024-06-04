package tictactoe;

import java.util.Random;

public class ComputerMedium extends Player {
    public ComputerMedium(State symbol) {
        super(symbol);
    }

    @Override
    public Integer[] move(Cell[][] board) {
        System.out.println("Making move level \"medium\"");
        if (winningMove(board, this.getSymbol()) != null) {
            Integer[] coordinates = winningMove(board, this.getSymbol());
            int i = coordinates[0];
            int j = coordinates[1];
            board[i][j].setState(this.getSymbol());
            return coordinates;
        }
        if (blockingMove(board) != null) {
            Integer[] coordinates = blockingMove(board);
            int i = coordinates[0];
            int j = coordinates[1];
            board[i][j].setState(this.getSymbol());
            return coordinates;
        }
        Random rand = new Random();
        while (true) {
            int i = rand.nextInt(3);
            int j = rand.nextInt(3);
            if (board[i][j].getState() == State.EMPTY) {
                board[i][j].setState(this.getSymbol());
                return new Integer[]{i, j};
            }
        }
    }

    public static Integer[] winningMove(Cell[][] board, State symbol) {
        return checkTwoInARow(symbol, board);
    }

    private Integer[] blockingMove(Cell[][] board) {
        State opponentSymbol;
        if (this.getSymbol() == State.X) {
            opponentSymbol = State.O;
        } else {
            opponentSymbol = State.X;
        }
        return checkTwoInARow(opponentSymbol, board);
    }

    public static Integer[] checkTwoInARow(State symbol, Cell[][] board) {
        Integer[] coordinates = checkHorizontal(symbol, board);
        if (coordinates != null) {
            return coordinates;
        }
        coordinates = checkVertical(symbol, board);
        if (coordinates != null) {
            return coordinates;
        }
        coordinates = checkDiagonal(symbol, board);
        if (coordinates != null) {
            return coordinates;
        }
        coordinates = checkAntiDiagonal(symbol, board);
        return coordinates;
    }

    public static Integer[] checkHorizontal(State symbol, Cell[][] board) {
        Integer[] coordinates = null;
        for (int i = 0; i < board.length; i++) {
            int temp = 0;
            Integer[] tempCoordinates = null;
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getState() == symbol) {
                    temp++;
                } else if (board[i][j].getState() == State.EMPTY) {
                    tempCoordinates = new Integer[]{i, j};
                }
            }
            if (temp == 2 && tempCoordinates != null) {
                coordinates = new Integer[]{tempCoordinates[0], tempCoordinates[1]};
            }
        }
        return coordinates;
    }

    public static Integer[] checkVertical(State symbol, Cell[][] board) {
        Integer[] coordinates = null;
        for (int j = 0; j < board.length; j++) {
            int temp = 0;
            Integer[] tempCoordinates = null;
            for (int i = 0; i < board[j].length; i++) {
                if (board[i][j].getState() == symbol) {
                    temp++;
                } else if (board[i][j].getState() == State.EMPTY) {
                    tempCoordinates = new Integer[]{i, j};
                }
            }
            if (temp == 2 && tempCoordinates != null) {
                coordinates = new Integer[]{tempCoordinates[0], tempCoordinates[1]};
            }
        }
        return coordinates;
    }

    public static Integer[] checkDiagonal(State symbol, Cell[][] board) {
        int count = 0;
        Integer[] coordinates = null;
        for (int i = 0; i <= 2; i++) {
            boolean temp = board[i][i].getState() == symbol;
            if (temp) {
                count++;
            } else if (board[i][i].getState() == State.EMPTY) {
                coordinates = new Integer[]{i, i};
            }
        }
        if (count != 2) {
            coordinates = null;
        }
        return coordinates;
    }

    public static Integer[] checkAntiDiagonal(State symbol, Cell[][] board) {
        int count = 0;
        Integer[] coordinates = null;
        for (int i = -1; i <= 1; i++) {
            boolean temp = board[i + 1][1 - i].getState() == symbol;
            if (temp) {
                count++;
            } else if (board[i + 1][1 - i].getState() == State.EMPTY) {
                coordinates = new Integer[]{i + 1, 1 - i};
            }
        }
        if (count != 2) {
            coordinates = null;
        }
        return coordinates;
    }
}
