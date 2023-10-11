package org.example.in;

import java.util.Scanner;

/**
 * The MenuInput class handles user input for menu actions.
 */
public class MenuInput {
    private Scanner scanner;

    /**
     * Constructs a MenuInput object with the provided Scanner.
     * @param scanner The Scanner object used for user input.
     */
    public MenuInput(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Reads the user's input for the chosen menu action.
     * @return The user's input as a String.
     */
    public String chooseMenuAction() {
        return scanner.nextLine();
    }
}
