package tictactoe;

public enum State {
    X("X"), O("O"), EMPTY(" ");
    private final String value;

    State(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
