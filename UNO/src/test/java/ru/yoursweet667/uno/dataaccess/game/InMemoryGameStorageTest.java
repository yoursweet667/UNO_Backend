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
        Mockito.verify(storage).createGame(game);

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

        Mockito.when(inMemoryGameStorage.getGame(GAME_ID)).thenReturn(Optional.of(game));

        //When
        Optional<Game> gameFromMap = inMemoryGameStorage.getGame(game.getGameId());

        String gameId = gameFromMap.get().getGameId();

        //Then
        assertThat(gameId).isEqualTo(game.getGameId());
    }
}
