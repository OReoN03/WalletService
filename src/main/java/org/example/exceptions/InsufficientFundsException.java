package org.example.exceptions;

/**
 * The InsufficientFundsException class represents an exception that is thrown
 * when there are insufficient funds to perform a certain operation.
 */
public class InsufficientFundsException extends Exception {
    /**
     * Constructs an InsufficientFundsException with the specified detail message.
     * @param message The detail message explaining the reason for the exception.
     */
    public InsufficientFundsException(String message) {
        super(message);
    }
}
