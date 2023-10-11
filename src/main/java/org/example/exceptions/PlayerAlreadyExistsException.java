package org.example.exceptions;

/**
 * The PlayerAlreadyExistsException class represents an exception that is thrown when attempting to create a player that already exists.
 */
public class PlayerAlreadyExistsException extends Exception {
    /**
     * Constructs a new PlayerAlreadyExistsException object with the specified error message.
     * @param message The error message describing the exception.
     */
    public PlayerAlreadyExistsException(String message) {
        super(message);
    }
}
