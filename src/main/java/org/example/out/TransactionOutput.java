package org.example.out;

import org.example.domain.Player;
import org.example.domain.Transaction;
import org.example.service.TransactionService;

import java.util.List;

/**
 * The TransactionOutput class handles displaying transaction history to the output console.
 */
public class TransactionOutput {
    private TransactionService transactionService;

    /**
     * Constructs a TransactionOutput object with the provided TransactionService.
     * @param transactionService The TransactionService object used for retrieving transaction data.
     */
    public TransactionOutput(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Displays the transaction history to the output console.
     */
    public void showTransactionHistory() {
        System.out.println();
        List<Transaction> transactions = transactionService.getTransactionRepository().findAll();
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("Transaction history:");
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }

    /**
     * Displays the transaction history for a specific player to the output console.
     * @param player The Player object for which to display the transaction history.
     */
    public void showTransactionHistoryForPlayer(Player player) {
        System.out.println();
        System.out.println("Transaction history for player: " + player.getName());
        for (Transaction transaction : transactionService.transactionsForPlayer(player)) {
            System.out.println(transaction);
        }
    }
}
