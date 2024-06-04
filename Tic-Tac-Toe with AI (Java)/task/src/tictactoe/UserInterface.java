package tictactoe;

import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    public String startInput() {
        String input;
        while (true) {
            System.out.println("Input command:");
            input = this.scanner.nextLine();
            if (!input.matches("exit|(start (user|hard|medium|easy)\s?(user|hard|medium|easy))")) {
                System.out.println("Bad parameters!");
            } else {
                break;
            }
        }

        return input;
    }

    public String coordinateInput() {
        String input;
        while (true) {
            System.out.println("Enter the coordinates:");
            input = this.scanner.nextLine();
            if (!input.matches("[1-3] [1-3]")) {
                printInvalidCoordinates();
            } else {
                break;
            }
        }
        return input;
    }

    public void printInvalidCoordinates() {
        System.out.println("Invalid coordinates");
    }
}
