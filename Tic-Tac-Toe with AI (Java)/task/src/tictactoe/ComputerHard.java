package tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ComputerHard extends Player {
    private Integer[] choice;
    public ComputerHard(State symbol) {
        super(symbol);
    }

    @Override
    public Integer[] move(Cell[][] board) {
        System.out.println("making move level \"hard\"");
        int outcome = minimax(board, true);
        int i = this.choice[0];
        int j = this.choice[1];
        board[i][j].setState(this.getSymbol());
        return this.choice;
    }

    private int score(Cell[][] board) {
        State opponent;
        if (this.getSymbol() == State.X) {
            opponent = State.O;
        } else {
            opponent = State.X;
        }
        if (GameController.checkWin(this.getSymbol(), board)) {
            return 10;
        } else if (GameController.checkWin(opponent, board)) {
            return -10;
        }
        return 0;
    }

    private Integer[][] getAvailableMoves(Cell[][] board) {
        ArrayList<Integer[]> moves = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getState() == State.EMPTY) {
                    moves.add(new Integer[]{i, j});
                }
            }
        }
        Integer[][] array = new Integer[moves.size()][2];
        array = moves.toArray(array);
        return array;
    }

    private int minimax(Cell[][] board, boolean isMaxTurn) {
        int score = score(board);
        if (GameController.checkDraw() || score == 10 || score == -10) {
            return score;
        }
        Integer[][] availableMoves = getAvailableMoves(board);
        if (availableMoves.length == 0) {
            return score;
        }
        List<Integer> scores = new ArrayList<>();
        List<Integer[]> moves = new ArrayList<>();
        for (int k = 0; k < availableMoves.length; k++) {
            Integer[] move = availableMoves[k];
            moves.add(move);
            int i = move[0];
            int j = move[1];
            State opponent;
            if (this.getSymbol() == State.X) {
                opponent = State.O;
            } else {
                opponent = State.X;
            }
            if (isMaxTurn) {
                board[i][j].setState(this.getSymbol());
                scores.add(minimax(board, false));
                board[i][j].setState(State.EMPTY);
            } else {
                board[i][j].setState(opponent);
                scores.add(minimax(board, true));
                board[i][j].setState(State.EMPTY);
            }
        }
        if (isMaxTurn) {
            int bestValue = Integer.MIN_VALUE;
            int index = 0;
            for (int i = 0; i < scores.size(); i++) {
                if (scores.get(i) > bestValue) {
                    bestValue = scores.get(i);
                    index = i;
                }
            }
            this.choice = moves.get(index);
            return bestValue;
        } else {
            int bestValue = Integer.MAX_VALUE;
            int index = 0;
            for (int i = 0; i < scores.size(); i++) {
                if (scores.get(i) < bestValue) {
                    bestValue = scores.get(i);
                    index = i;
                }
            }
            return bestValue;
        }
    }
}
