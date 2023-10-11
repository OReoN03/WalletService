package org.example.repository;

import org.example.domain.Player;
import org.example.domain.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The TransactionRepositoryImpl class represents a data access object for managing Transaction objects.
 * It uses a HashMap to store the transactions with their IDs as keys.
 */
public class TransactionRepositoryImpl implements TransactionRepository {
    private Map<Long, Transaction> transactions = new HashMap<>();

    /**
     * Retrieves a transaction by its ID.
     * @param id the ID of the transaction to retrieve
     * @return the Transaction object associated with the given ID, or null if no such transaction exists
     */
    @Override
    public Transaction get(long id) {
        return transactions.get(id);
    }

    /**
     * Adds a new transaction to the repository.
     * @param transaction the Transaction object to add
     */
    @Override
    public void add(Transaction transaction) {
        transactions.put(transaction.getId(), transaction);
    }

    /**
     * Updates an existing transaction in the repository.
     * @param transaction the Transaction object to update
     */
    @Override
    public void update(Transaction transaction) {
        transactions.put(transaction.getId(), transaction);
    }

    /**
     * Removes a transaction from the repository.
     * @param transaction the Transaction object to remove
     */
    @Override
    public void remove(Transaction transaction) {
        transactions.remove(transaction.getId());
    }

    /**
     * Retrieves a list of all transactions in the repository.
     * @return a List of all Transaction objects in the repository
     */
    public List<Transaction> findAll() {
        return new ArrayList<>(transactions.values());
    }

    /**
     * Retrieves a list of all transactions for a given player.
     * @param player the Player object to retrieve transactions for
     * @return a List of all Transaction objects associated with the given player
     */
    public List<Transaction> findForPlayer(Player player) {
        List<Transaction> transactionList = new ArrayList<>();
        for (Map.Entry<Long, Transaction> entry : transactions.entrySet()) {
            if (entry.getValue().getPlayer().equals(player)) {
                transactionList.add(entry.getValue());
            }
        }
        return  transactionList;
    }
}
