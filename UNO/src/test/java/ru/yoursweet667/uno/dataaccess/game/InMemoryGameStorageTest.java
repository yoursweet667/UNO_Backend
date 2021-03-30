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
    void updateGame_putGameInMap() {
        //Give
        Game game = new Game(GAME_ID,null,null,null,
                null,null);

        //When
        inMemoryGameStorage.updateGame(game);

        //Then
        Optional<Game> returnedGame = inMemoryGameStorage.getGame(GAME_ID);
        assertThat(returnedGame.isPresent());
        assertThat(returnedGame.get()).isEqualTo(game);
    }

    @Test
    void deleteGame_removeGame() {
        //Give
        Game game = new Game(GAME_ID,null,null,null,
                null,null);

        //When
        inMemoryGameStorage.deleteGame(game.getGameId());

        //Then
        assertThat(inMemoryGameStorage.getGame(GAME_ID)).isEmpty();
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