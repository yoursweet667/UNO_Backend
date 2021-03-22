package ru.yoursweet667.uno.service.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.yoursweet667.uno.dataaccess.game.GameStorage;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.GameState;
import ru.yoursweet667.uno.service.model.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class GameServiceImplTest {

    private final static String GAME_ID = "gameId";
    private static final String PLAYER_ID = "playerId";

    @Mock
    private GameStorage storage;

    @InjectMocks
    private GameServiceImpl gameServiceImpl;

    @BeforeEach
    private void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createGame_createsGame() {
        //Given+When
        Game game = gameServiceImpl.createGame();

        //Then
        assertThat(game).isNotNull();
        assertThat(game.getGameId()).isNotNull();
        assertThat(game.getPlayers()).isEmpty();
        assertThat(game.getGameState()).isEqualTo(GameState.GAME_CREATED);
        assertThat(game.getDeck()).hasSize(108);
        assertThat(game.getCardsInTheGame()).isEmpty();
        assertThat(game.getEvents()).isEmpty();

        Mockito.verify(storage).createGame(game);
    }

    @Test
    void addPlayerToGame_addPlayerToGame() {
        //Given
        Game game = new Game(GAME_ID, new HashMap<>(), null,
                null, null, null);
        Mockito.when(storage.getGame(GAME_ID)).thenReturn(Optional.of(game));

        //When
        Player player = gameServiceImpl.addPlayerToGame(GAME_ID, "playerName");

        //Then
        assertThat(game.getPlayers()).containsKey(player.getPlayerId());
        assertThat(game.getPlayers().get(player.getPlayerId())).isEqualTo(player);
    }

    @Test
    void removePlayerFromGame_removePlayer() {
        //Given
        Map<String, Player> players = new HashMap<>();
        Player player = new Player(PLAYER_ID, null, null);
        players.put(player.getPlayerId() ,player);

        Game game = new Game(GAME_ID, players, null,
                null, null, null);

        Mockito.when(storage.getGame(GAME_ID)).thenReturn(Optional.of(game));

        //When
        gameServiceImpl.removePlayerFromGame(GAME_ID, PLAYER_ID);

        //Then
        assertThat(game.getPlayers()).doesNotContainKey(player.getPlayerId());
    }

    @Test
    void getGame_returnGameFromStorage() {
        //Given
        Game game = new Game(GAME_ID, null, null,
                null, null, null);
        Mockito.when(storage.getGame(GAME_ID)).thenReturn(Optional.of(game));

        //When
        Optional<Game> gameFromStorage = gameServiceImpl.getGame(GAME_ID);

        //Then
        assertThat(gameFromStorage.get()).isEqualTo(game);
    }
}