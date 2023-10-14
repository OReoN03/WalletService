package org.example.service;

import org.example.domain.Player;
import org.example.domain.Transaction;
import org.example.exceptions.InsufficientFundsException;
import org.example.exceptions.NotUniqueTransactionIdException;
import org.example.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The TransactionService class provides methods for crediting, debiting, and retrieving transactions for a player.
 */
public class TransactionService {
    private TransactionRepository transactionRepository;

    /**
     * Constructs a TransactionService object with the provided TransactionRepository.
     * @param transactionRepository The TransactionRepository object used for storing transactions.
     */
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * Retrieves the TransactionRepository used by the service.
     * @return The TransactionRepository object.
     */
    public TransactionRepository getTransactionRepository() {
        return transactionRepository;
    }

    /**
     * Credits the player's balance with the specified amount and creates a new transaction.
     * @param player The player to credit.
     * @param amount The amount to credit.
     * @param id The ID of the transaction.
     * @throws NotUniqueTransactionIdException If the transaction ID is not unique.
     */
    public void credit(Player player, double amount, long id) throws NotUniqueTransactionIdException {
        if (player.getTransactions().get(id) != null) {
            throw new NotUniqueTransactionIdException("Transaction's id is not unique.");
        }
        player.setBalance(player.getBalance() + amount);
        Transaction transaction = new Transaction(id, amount, Transaction.Type.CREDIT, player, LocalDateTime.now());
        player.getTransactions().put(id, transaction);
        transactionRepository.add(transaction);
    }

    /**
     * Debits the player's balance with the specified amount and creates a new transaction.
     * @param player The player to debit.
     * @param amount The amount to debit.
     * @param id The ID of the transaction.
     * @throws NotUniqueTransactionIdException If the transaction ID is not unique.
     * @throws InsufficientFundsException If the player has insufficient funds.
     */
    public void debit(Player player, double amount, long id) throws NotUniqueTransactionIdException, InsufficientFundsException {
        if (player.getTransactions().get(id) != null) {
            throw new NotUniqueTransactionIdException("Transaction's id is not unique.");
        }

        double amountAfterDebit = player.getBalance() - amount;
        if (amountAfterDebit < 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        player.setBalance(amountAfterDebit);
        Transaction transaction = new Transaction(id, amount, Transaction.Type.DEBIT, player, LocalDateTime.now());
        player.getTransactions().put(id, transaction);
        transactionRepository.add(transaction);
    }

    /**
     * Makes a transaction for the player based on the specified type (credit or debit).
     * @param player The player to perform the transaction for.
     * @param amount The amount of the transaction.
     * @param transactionId The ID of the transaction.
     * @param type The type of the transaction (credit or debit).
     * @throws NotUniqueTransactionIdException If the transaction ID is not unique.
     * @throws InsufficientFundsException If the player has insufficient funds for a debit transaction.
     */
    public void makeTransaction(Player player, double amount, long transactionId, Transaction.Type type) throws NotUniqueTransactionIdException, InsufficientFundsException {
        if (type == Transaction.Type.CREDIT) {
            credit(player, amount, transactionId);
        } else if (type == Transaction.Type.DEBIT) {
            debit(player, amount, transactionId);
        }
    }

    /**
     * Retrieves a list of transactions for the specified player.
     * @param player The player to retrieve transactions for.
     * @return The list of transactions.
     */
    public List<Transaction> transactionsForPlayer(Player player) {
        return transactionRepository.findForPlayer(player);
    }
}
