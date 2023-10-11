package org.example.repository;

import org.example.domain.Player;

import java.util.List;

/**
 * The PlayerRepository interface represents a data access object for managing Player objects.
 */
public interface PlayerRepository {
    /**
     * Retrieves a player by their name.
     * @param name the name of the player to retrieve
     * @return the Player object associated with the given name, or null if no such player exists
     */
    Player get(String name);
    /**
     * Adds a new player to the repository.
     * @param player the Player object to add
     */
    void add(Player player);
    /**
     * Updates an existing player in the repository.
     * @param player the Player object to update
     */
    void update(Player player);
    /**
     * Removes a player from the repository.
     * @param player the Player object to remove
     */
    void remove(Player player);
    /**
     * Retrieves a list of all players in the repository.
     * @return a List of all Player objects in the repository
     */
    List<Player> findAll();
}
