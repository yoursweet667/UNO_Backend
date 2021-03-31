package ru.yoursweet667.uno.service.event.processor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.yoursweet667.uno.service.model.EventType;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.event.Event;
import ru.yoursweet667.uno.service.model.event.JoinGameEvent;
import ru.yoursweet667.uno.service.model.event.StartGameEvent;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;


import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;


public class JoinGameProcessorTest {

    private final JoinGameProcessor joinGameProcessor = new JoinGameProcessor();

    @Mock
    private BiConsumer<String, Event> biConsumer;

    @BeforeEach
    private void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void doProcess_gameHasNoPlayers_joinPlayerAndDoNotSpawnNextEvent() {
        //Given
        Map<String, Player> players = new HashMap<>();
        Player player = new Player("playerId", null, null);
        Game game = new Game(null, players, null,
                null, null, null);
        JoinGameEvent event = new JoinGameEvent(123, EventType.JOIN_GAME, player);

        //When
        joinGameProcessor.doProcess(event, game, biConsumer);

        //Then
        assertThat(game.getPlayers().get("playerId")).isEqualTo(player);
        StartGameEvent startGameEvent = new StartGameEvent
                (event.getEventId() + 1, EventType.START_GAME);
        Mockito.verify(biConsumer, never()).accept(any(), any());
    }

    @Test
    void doProcess_gameContains2Players_SpawnNextEvent(){
        //Given
        Map<String, Player> players = new HashMap<>();
        Player playerForGame = new Player("somePlayerId", null, null);
        Player player = new Player("playerId", null, null);
        players.put(playerForGame.getPlayerId(), playerForGame);
        Game game = new Game(null, players, null,
                null, null, null);
        JoinGameEvent event = new JoinGameEvent(123, EventType.JOIN_GAME, player);

        //When
        joinGameProcessor.doProcess(event, game, biConsumer);

        //Then
        assertThat(game.getPlayers().size()).isEqualTo(2);
        StartGameEvent startGameEvent = new StartGameEvent
                (event.getEventId() + 1, EventType.START_GAME);
        Mockito.verify(biConsumer).accept(game.getGameId(), startGameEvent);
    }
}

