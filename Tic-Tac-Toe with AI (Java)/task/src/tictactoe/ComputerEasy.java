package tictactoe;

import java.util.Random;

public class ComputerEasy extends Player {
    public ComputerEasy(State symbol) {
        super(symbol);
    }

    @Override
    public Integer[] move(Cell[][] board) {
        System.out.println("Making move level \"easy\"");
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
}
