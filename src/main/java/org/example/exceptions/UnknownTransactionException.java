package org.example.exceptions;

/**
 * The UnknownTransactionException class represents an exception that is thrown
 * when an unknown or invalid transaction occurs.
 */
public class UnknownTransactionException extends Exception {
    /**
     * Constructs an UnknownTransactionException with the specified detail message.
     * @param message The detail message explaining the reason for the exception.
     */
    public UnknownTransactionException(String message) {
        super(message);
    }
}
