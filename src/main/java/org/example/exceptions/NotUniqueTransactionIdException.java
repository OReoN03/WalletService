package org.example.exceptions;

/**
 * The NotUniqueTransactionIdException class represents an exception that is thrown
 * when a transaction ID is not unique.
 */
public class NotUniqueTransactionIdException extends Exception {
    /**
     * Constructs an NotUniqueTransactionIdException with the specified detail message.
     * @param message The detail message explaining the reason for the exception.
     */
    public NotUniqueTransactionIdException(String message) {
        super(message);
    }
}
