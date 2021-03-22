package ru.yoursweet667.uno.dataaccess.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.yoursweet667.uno.service.model.Game;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryGameStorageTest {

    private final static String GAME_ID = "gameId";

    @Mock
    private GameStorage storage;

    @Mock
    private InMemoryGameStorage inMemoryGameStorage;

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
        storage.createGame(game);

        //Then
        Optional<Game> gameFromStorage = storage.getGame(GAME_ID);

        assertThat(gameFromStorage).isPresent();
        assertThat(gameFromStorage.get()).isEqualTo(game);
    }

    @Test
    void updateGame_putGameInMap() {
        //Give
        Game game = new Game(GAME_ID,null,null,null,
                null,null);

        //When
        storage.updateGame(game);

        //Then
        Mockito.verify(storage).updateGame(game);
    }

    @Test
    void deleteGame_removeGame() {
        //Give
        Game game = new Game(GAME_ID,null,null,null,
                null,null);

        //When
        storage.deleteGame(game.getGameId());

        //Then
        Mockito.verify(storage).deleteGame(game.getGameId());
    }

    @Test
    void getGame_returnGame() {
        //Give
        Game game = new Game(GAME_ID, null, null,
                null, null, null);

        storage.createGame(game);

        //When
        Optional<Game> resultGame = inMemoryGameStorage.getGame(game.getGameId());

        //Then
        assertThat(resultGame).isPresent();
        assertThat(resultGame.get()).isEqualTo(game);
    }
}