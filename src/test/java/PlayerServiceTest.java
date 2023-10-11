import org.example.domain.Player;
import org.example.exceptions.PlayerAlreadyExistsException;
import org.example.exceptions.PlayerNotFoundException;
import org.example.repository.PlayerRepository;
import org.example.service.PlayerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The PlayerServiceTest class is responsible for testing the methods in the PlayerService class.
 */
@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {
    private PlayerRepository playerRepository;
    private PlayerService playerService;

    /**
     * Sets up the necessary dependencies and configuration before each test.
     */
    @BeforeEach
    public void setUp() {
        playerRepository = Mockito.mock(PlayerRepository.class);
        playerService = new PlayerService(playerRepository);
    }

    /**
     * Tests the register method of the PlayerService class for registering a new player.
     * @throws PlayerAlreadyExistsException if the player already exists.
     */
    @Test
    public void testRegisterNewPlayer() throws PlayerAlreadyExistsException {
        String name = "John";
        String password = "password";
        Player player = new Player(name, password, 0.0);

        when(playerRepository.get(name)).thenReturn(null);

        Player registeredPlayer = playerService.register(name, password);

        assertThat(registeredPlayer).isEqualTo(player);
        verify(playerRepository).add(player);
    }

    /**
     * Tests the register method of the PlayerService class when trying to register an existing player.
     */
    @Test
    public void testRegisterExistingPlayer() {
        String name = "John";
        String password = "password";

        when(playerRepository.get(name)).thenReturn(new Player(name, password, 0.0));

        assertThatExceptionOfType(PlayerAlreadyExistsException.class)
                .isThrownBy(() -> playerService.register(name, password));

        verify(playerRepository, never()).add(any());
    }

    /**
     * Tests the login method of the PlayerService class with valid credentials.
     * @throws PlayerNotFoundException if the player is not found.
     */
    @Test
    public void testLoginWithValidCredentials() throws PlayerNotFoundException {
        String name = "John";
        String password = "password";
        Player player = new Player(name, password, 0.0);

        when(playerRepository.get(name)).thenReturn(player);

        Player loggedInPlayer = playerService.login(name, password);

        assertThat(loggedInPlayer).isEqualTo(player);
    }

    /**
     * Tests the login method of the PlayerService class with invalid credentials.
     */
    @Test
    public void testLoginWithInvalidCredentials() {
        String name = "John";
        String password = "password";

        when(playerRepository.get(name)).thenReturn(null);

        assertThatExceptionOfType(PlayerNotFoundException.class).isThrownBy(() -> {
            playerService.login(name, password);
        });
    }

    /**
     * Tests the authenticate method of the PlayerService class when using the "register" mode.
     * @throws PlayerAlreadyExistsException if the player already exists.
     * @throws PlayerNotFoundException if the player is not found.
     */
    @Test
    public void testAuthenticateWithRegisterMode() throws PlayerAlreadyExistsException, PlayerNotFoundException {
        String name = "John";
        String password = "password";
        Player player = new Player(name, password, 0.0);

        when(playerRepository.get(name)).thenReturn(null);

        Player authenticatedPlayer = playerService.authenticate("register", name, password);

        assertThat(authenticatedPlayer).isEqualTo(player);
    }

    /**
     * Tests the authenticate method of the PlayerService class when using the "login" mode.
     * @throws PlayerAlreadyExistsException if the player already exists.
     * @throws PlayerNotFoundException if the player is not found.
     */
    @Test
    public void testAuthenticateWithLoginMode() throws PlayerAlreadyExistsException, PlayerNotFoundException {
        String name = "John";
        String password = "password";
        Player player = new Player(name, password, 0.0);

        when(playerRepository.get(name)).thenReturn(player);

        Player authenticatedPlayer = playerService.authenticate("login", name, password);

        assertThat(authenticatedPlayer).isEqualTo(player);
    }

    /**
     * Tests the getAllPlayers method of the PlayerService class to retrieve all players.
     */
    @Test
    public void testGetAllPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("John", "password1", 0.0));
        players.add(new Player("Alice", "password2", 0.0));

        when(playerRepository.findAll()).thenReturn(players);

        List<Player> allPlayers = playerService.getAllPlayers();
        assertThat(players).isEqualTo(allPlayers);
    }
}
