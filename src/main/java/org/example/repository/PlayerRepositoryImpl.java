package org.example.repository;

import org.example.domain.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The PlayerRepositoryImpl class represents a data access object for managing Player objects.
 * It uses a HashMap to store the players with their names as keys.
 */
public class PlayerRepositoryImpl implements PlayerRepository {

    private Map<String, Player> playerMap = new HashMap<>();

    /**
     * Retrieves a player by their name.
     * @param name the name of the player to retrieve
     * @return the Player object associated with the given name, or null if no such player exists
     */
    @Override
    public Player get(String name) {
        return playerMap.get(name);
    }

    /**
     * Adds a new player to the repository.
     * @param player the Player object to add
     */
    @Override
    public void add(Player player) {
        playerMap.put(player.getName(), player);
    }

    /**
     * Updates an existing player in the repository.
     * @param player the Player object to update
     */
    @Override
    public void update(Player player) {
        playerMap.put(player.getName(), player);
    }

    /**
     * Removes a player from the repository.
     * @param player the Player object to remove
     */
    @Override
    public void remove(Player player) {
        playerMap.remove(player.getName());
    }

    /**
     * Retrieves a list of all players in the repository.
     * @return a List of all Player objects in the repository
     */
    @Override
    public List<Player> findAll() {
        return new ArrayList<>(playerMap.values());
    }
}
