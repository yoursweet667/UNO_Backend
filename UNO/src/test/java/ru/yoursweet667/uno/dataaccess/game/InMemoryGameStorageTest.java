package ru.yoursweet667.uno.dataaccess.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.yoursweet667.uno.dataaccess.game.exception.InvalidGameStorageRequestException;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryGameStorageTest {

    private final static String GAME_ID = "gameId";

    private InMemoryGameStorage inMemoryGameStorage = new InMemoryGameStorage();

    @BeforeEach
    private void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createGame_putGameInMap() {
        //Given
        Game game = new Game(GAME_ID,null,null,null,
                null,null);

        //When
        inMemoryGameStorage.createGame(game);

        //Then
        Optional<Game> gameFromStorage = inMemoryGameStorage.getGame(GAME_ID);

        assertThat(gameFromStorage).isPresent();
        assertThat(gameFromStorage.get()).isEqualTo(game);
    }

    @Test
    void createGame_gameAlreadyExists_exception() {
        //Given
        Game game = new Game(GAME_ID,null,null,null,
                null,null);
        inMemoryGameStorage.createGame(game);

        //When + Then
        Assertions.assertThatThrownBy(() -> inMemoryGameStorage.createGame(game))
                .isInstanceOf(InvalidGameStorageRequestException.class);
    }

    @Test
    void updateGame_putGameInMap() {
        //Give
        Map<String, Player> players = new HashMap<>();
        Game game = new Game(GAME_ID, Map.of(),null,null,
                null,null);
        inMemoryGameStorage.createGame(game);
        Optional<Game> gameBeforeChanges = inMemoryGameStorage.getGame(GAME_ID);
        Player player = new Player("playerId", null, null);

        Game gameWithChanges = new Game(GAME_ID, players,null,null,
                null,null);
        gameWithChanges.getPlayers().put(player.getPlayerId(), player);

        //When
        inMemoryGameStorage.updateGame(gameWithChanges);

        //Then
        Optional<Game> returnedGameWihChanges = inMemoryGameStorage.getGame(GAME_ID);
        assertThat(gameBeforeChanges).isPresent();
        assertThat(gameBeforeChanges.get()).isNotEqualTo(gameWithChanges);
        assertThat(returnedGameWihChanges).isPresent();
        assertThat(returnedGameWihChanges).isPresent();
        assertThat(returnedGameWihChanges.get().getGameId()).isEqualTo(game.getGameId());
    }

    @Test
    void updateGame_gameNotFound_exception() {
        //Give
        Game game = new Game(GAME_ID,null,null,null,
                null,null);

        //When + Then
        Assertions.assertThatThrownBy(() -> inMemoryGameStorage.updateGame(game))
                .isInstanceOf(InvalidGameStorageRequestException.class);
    }

    @Test
    void deleteGame_removeGame() {
        //Give
        Game game = new Game(GAME_ID,null,null,null,
                null,null);
        inMemoryGameStorage.createGame(game);

        //When
        inMemoryGameStorage.deleteGame(game.getGameId());

        //Then
        assertThat(inMemoryGameStorage.getGame(GAME_ID)).isEmpty();
    }

    @Test
    void deleteGame_gameNotFound_exception() {
        //Give
        Game game = new Game(GAME_ID,null,null,null,
                null,null);

        //When + Then
        Assertions.assertThatThrownBy(() -> inMemoryGameStorage.deleteGame(game.getGameId()))
                .isInstanceOf(InvalidGameStorageRequestException.class);
    }

    @Test
    void getGame_returnGame() {
        //Give
        Game game = new Game(GAME_ID, null, null,
                null, null, null);

        inMemoryGameStorage.createGame(game);

        //When
        Optional<Game> resultGame = inMemoryGameStorage.getGame(game.getGameId());

        //Then
        assertThat(resultGame).isPresent();
        assertThat(resultGame.get()).isEqualTo(game);
    }
}