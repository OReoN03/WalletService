package org.example.service;

import org.example.domain.Player;
import org.example.exceptions.PlayerAlreadyExistsException;
import org.example.exceptions.PlayerNotFoundException;
import org.example.repository.PlayerRepository;

import java.util.List;

/**
 * The PlayerService class handles the business logic operations related to players.
 */
public class PlayerService {
    private PlayerRepository playerRepository;

    /**
     * Constructs a PlayerService object with the provided PlayerRepository.
     * @param playerRepository The PlayerRepository object used for accessing player data.
     */
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    /**
     * Registers a new player with the given name and password.
     * @param name The name of the player.
     * @param password The password of the player.
     * @return The registered Player object.
     * @throws PlayerAlreadyExistsException If a player with the given name already exists.
     */
    public Player register(String name, String password) throws PlayerAlreadyExistsException {
        Player player = null;
        if (playerRepository.get(name) == null) {
            player = new Player(name, password, 0.0);
            playerRepository.add(player);
            return player;
        }
        else throw new PlayerAlreadyExistsException("Player already exists");
    }

    /**
     * Authenticates a player by verifying the provided name and password.
     * @param name The name of the player.
     * @param password The password of the player.
     * @return The authenticated Player object.
     * @throws PlayerNotFoundException If the player with the given name is not found.
     */
    public Player login(String name, String password) throws PlayerNotFoundException {
        Player player = playerRepository.get(name);
        if (player != null && player.getPassword().equals(password)) return player;
        else throw new PlayerNotFoundException("Player not found");
    }

    /**
     * Authenticate a player based on the provided mode (register or login).
     * @param mode The authentication mode (register or login).
     * @param username The username of the player.
     * @param password The password of the player.
     * @return The authenticated Player object.
     * @throws PlayerAlreadyExistsException If a player with the given name already exists.
     * @throws PlayerNotFoundException If the player with the given name is not found.
     */
    public Player authenticate(String mode, String username, String password) throws PlayerAlreadyExistsException, PlayerNotFoundException {
        Player player = null;
        if (mode.equalsIgnoreCase("register")) {
            player = register(username, password);
        } else if (mode.equalsIgnoreCase("login")) {
            player = login(username, password);
        } else {
            System.out.println("Unknown authentication mode");
            return authenticate(mode, username, password);
        }
        return player;
    }

    /**
     * Retrieves all players from the player repository.
     * @return A list of all players.
     */
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    /**
     * Finds a player by the given name.
     * @param name The name of the player.
     * @return The Player object with the given name, or null if not found.
     */
    public Player findPlayer(String name) {
        return playerRepository.get(name);
    }
}
