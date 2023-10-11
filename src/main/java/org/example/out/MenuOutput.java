package org.example.out;

/**
 * The MenuOutput class handles printing menu options and messages.
 */
public class MenuOutput {
    /**
     * Prints the initial menu options for authentication and exiting.
     */
    public void printEnterMenu() {
        System.out.println();
        System.out.println("Menu");
        System.out.println("1. Authenticate");
        System.out.println("2. Exit");
    }

    /**
     * Prints the main menu options for balance, transactions, making transactions, and logging out.
     */
    public void printMainMenu() {
        System.out.println();
        System.out.println("Menu");
        System.out.println("1. Balance");
        System.out.println("2. Transactions");
        System.out.println("3. Make transaction");
        System.out.println("4. Log out");
    }
}
