package tictactoe;

public class User extends Player {
    private final UserInterface ui;

    public User(State symbol, UserInterface ui) {
        super(symbol);
        this.ui = ui;
    }

    @Override
    public Integer[] move(Cell[][] board) {
        while (true) {
            String input = this.ui.coordinateInput();
            String[] parts = input.split(" ");
            Integer[] coordinates = new Integer[]{Integer.parseInt(parts[0]) - 1, Integer.parseInt(parts[1]) - 1};
            int i = coordinates[0];
            int j = coordinates[1];
            if (board[i][j].getState() == State.EMPTY) {
                board[i][j].setState(this.getSymbol());
                return coordinates;
            }
            this.ui.printInvalidCoordinates();
        }
    }
}
