package tictactoe;
public abstract class Player {
    private final State symbol;

    public Player(State symbol) {
        this.symbol = symbol;
    }

    public State getSymbol() {
        return this.symbol;
    }

    public abstract Integer[] move(Cell[][] board);
}
