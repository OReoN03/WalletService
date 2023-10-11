package org.example.out;

import org.example.service.PlayerService;

/**
 * The PlayerOutput class handles displaying player account information.
 */
public class PlayerOutput {
    private PlayerService playerService;

    /**
     * Constructs a PlayerOutput object with the provided PlayerService.
     * @param playerService The PlayerService object used to retrieve player information.
     */
    public PlayerOutput(PlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     * Displays the player account information based on the provided name.
     * @param name The name of the player.
     */
    public void showPlayerAccount(String name) {
        System.out.println(playerService.findPlayer(name));
    }
}
