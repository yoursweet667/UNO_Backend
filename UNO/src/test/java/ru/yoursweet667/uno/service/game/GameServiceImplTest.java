package ru.yoursweet667.uno.service.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.yoursweet667.uno.service.event.EventHandlerRegistry;
import ru.yoursweet667.uno.service.event.EventServiceImpl;
import ru.yoursweet667.uno.service.model.Game;

import java.beans.EventHandler;

import static org.assertj.core.api.Assertions.assertThat;

public class GameServiceImplTest {

    private final static String GAME_ID = "gameId";
    private static final String PLAYER_ID = "playerId";

    @Mock
    private GameService gameService;
    @Mock
    private EventHandlerRegistry eventHandlerRegistry;

    @InjectMocks
    private EventServiceImpl eventService;

    @BeforeEach
    private void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void createGame_createsGame() {

        //Given+When
        Game game = gameService.createGame();

        //Then
        assertThat(game).isNotNull();

    }

}
