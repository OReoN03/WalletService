package org.example.repository;

import org.example.domain.Player;
import org.example.domain.Transaction;

import java.util.List;

/**
 * The TransactionRepository interface represents a data access object for managing Transaction objects.
 */
public interface TransactionRepository {
    /**
     * Retrieves a transaction by its ID.
     * @param id the ID of the transaction to retrieve
     * @return the Transaction object associated with the given ID, or null if no such transaction exists
     */
    Transaction get(long id);

    /**
     * Adds a new transaction to the repository.
     * @param transaction the Transaction object to add
     */
    void add(Transaction transaction);

    /**
     * Updates an existing transaction in the repository.
     * @param transaction the Transaction object to update
     */
    void update(Transaction transaction);

    /**
     * Removes a transaction from the repository.
     * @param transaction the Transaction object to remove
     */
    void remove(Transaction transaction);

    /**
     * Retrieves a list of all transactions in the repository.
     * @return a List of all Transaction objects in the repository
     */
    List<Transaction> findAll();

    /**
     * Retrieves a list of all transactions associated with a given player.
     * @param player the Player object to retrieve transactions for
     * @return a List of all Transaction objects associated with the given player
     */
    List<Transaction> findForPlayer(Player player);
}
