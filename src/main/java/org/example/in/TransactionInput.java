package org.example.in;

import org.example.domain.Transaction;
import org.example.exceptions.UnknownTransactionException;

import java.util.Scanner;

/**
 * The TransactionInput class handles user input related to transactions.
 */
public class TransactionInput {
    private Scanner scanner;

    /**
     * Constructs a TransactionInput object with the provided Scanner.
     * @param scanner The Scanner object used for user input.
     */
    public TransactionInput(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Prompts the user to enter the transaction amount and validates it.
     * @return The transaction amount entered by the user as a double.
     */
    public double enterAmount() {
        System.out.println("Enter amount: ");
        double amount = scanner.nextDouble();
        if (amount < 0) {
            System.out.println("Amount can't be negative number");
            return enterAmount();
        }
        return amount;
    }

    /**
     * Prompts the user to enter the transaction ID.
     * @return The transaction ID entered by the user as a long.
     */
    public long enterTransactionId() {
        System.out.println("Enter transaction ID: ");
        return scanner.nextLong();
    }

    /**
     * Prompts the user to enter the transaction type and validates it.
     * @return The transaction type entered by the user as a Transaction.Type enum.
     * @throws UnknownTransactionException if the entered transaction type is unknown.
     */
    public Transaction.Type enterTransactionType() throws UnknownTransactionException {
        System.out.println("Enter transaction type: \"debit\" or \"credit\"");
        String type = scanner.next();

        if (type.equalsIgnoreCase("debit")) return Transaction.Type.DEBIT;
        else if (type.equalsIgnoreCase("credit")) return Transaction.Type.CREDIT;
        else throw new UnknownTransactionException("Unknown transaction type");
    }
}
