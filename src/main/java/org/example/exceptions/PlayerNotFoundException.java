package org.example.exceptions;

/**
 * The PlayerNotFoundException class represents an exception that is thrown
 * when a player could not be found.
 */
public class PlayerNotFoundException extends Exception {
    /**
     * Constructs a PlayerNotFoundException with the specified detail message.
     * @param message The detail message explaining the reason for the exception.
     */
    public PlayerNotFoundException(String message) {
        super(message);
    }
}
