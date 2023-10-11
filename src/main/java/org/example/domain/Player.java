package org.example.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A class representing a player in the system. Stores information about the player's name, password, balance, and transactions.
 */
public class Player {
    private String name;
    private String password;
    private double balance;
    private Map<Long, Transaction> transactions;

    /**
     * Constructor for the Player class.
     *
     * @param name     the player's name
     * @param password the player's password
     * @param balance  the player's balance
     */
    public Player(String name, String password, double balance) {
        this.name = name;
        this.password = password;
        this.balance = balance;
        this.transactions = new HashMap<>();
    }

    /**
     * Method for getting the player's name.
     *
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Method for setting the player's name.
     *
     * @param name the player's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method for getting the player's password.
     *
     * @return the player's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method for setting the player's password.
     *
     * @param password the player's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method for getting the player's balance.
     *
     * @return the player's balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Method for setting the player's balance.
     *
     * @param balance the player's balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Method for getting the player's transactions.
     *
     * @return the player's transactions
     */
    public Map<Long, Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Overridden toString method.
     *
     * @return a string representation of the Player object
     */
    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }

    /**
     * Overridden equals method.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Double.compare(player.balance, balance) == 0 && name.equals(player.name) && password.equals(player.password) && Objects.equals(transactions, player.transactions);
    }

    /**
     * Overridden hashCode method.
     *
     * @return the hash code of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, password, balance, transactions);
    }
}
