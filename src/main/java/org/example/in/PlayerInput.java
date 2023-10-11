package org.example.in;

import java.util.Scanner;

/**
 * The PlayerInput class handles user input related to player authentication.
 */
public class PlayerInput {
    private Scanner scanner;

    /**
     * Constructs a PlayerInput object with the provided Scanner.
     * @param scanner The Scanner object used for user input.
     */
    public PlayerInput(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Prompts the user to enter the authentication mode (register or login).
     * @return The user's input as a String.
     */
    public String enterAuthenticationMode() {
        System.out.println("Enter \"register\" or \"login\": ");
        return scanner.nextLine();
    }

    /**
     * Prompts the user to enter the username.
     * @return The user's input as a String.
     */
    public String enterUsername() {
        System.out.println("Enter username: ");
        return scanner.nextLine();
    }

    /**
     * Prompts the user to enter the password.
     * @return The user's input as a String.
     */
    public String enterPassword() {
        System.out.println("Enter password: ");
        return scanner.nextLine();
    }
}
