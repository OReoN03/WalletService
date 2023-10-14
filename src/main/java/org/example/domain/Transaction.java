package org.example.domain;

import java.time.LocalDateTime;

/**
 * A class representing a transaction in the system. Stores information about the transaction's ID, amount, type, player, date and time.
 */
public class Transaction {
    private long id;
    private double amount;
    private Type type;
    private Player player;
    private LocalDateTime dateTime;

    /**
     * Constructor for the Transaction class.
     *
     * @param id      the transaction's ID
     * @param amount  the transaction's amount
     * @param type    the transaction's type (debit or credit)
     * @param player  the player associated with the transaction
     * @param dateTime the transaction's date and time
     */
    public Transaction(long id, double amount, Type type, Player player, LocalDateTime dateTime) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.player = player;
        this.dateTime = dateTime;
    }

    /**
     * Method for getting the transaction's ID.
     *
     * @return the transaction's ID
     */
    public long getId() {
        return id;
    }

    /**
     * Method for getting the transaction's amount.
     *
     * @return the transaction's amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Method for getting the transaction's type.
     *
     * @return the transaction's type
     */
    public Type getType() {
        return type;
    }

    /**
     * Method for getting the player associated with the transaction.
     *
     * @return the player associated with the transaction
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Method for getting the transaction's date and time.
     *
     * @return the transaction's date and time
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Enum representing the two types of transactions: debit and credit.
     */
    public enum Type {
        DEBIT, CREDIT
    }

    /**
     * Overridden toString method.
     *
     * @return a string representation of the Transaction object
     */
    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", type=" + type +
                ", player=" + player.getName() +
                '}';
    }
}
