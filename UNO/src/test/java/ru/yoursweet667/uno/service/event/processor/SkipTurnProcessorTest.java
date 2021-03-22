package ru.yoursweet667.uno.service.event.processor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.yoursweet667.uno.service.model.EventType;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.event.EndTurnEvent;
import ru.yoursweet667.uno.service.model.event.Event;
import ru.yoursweet667.uno.service.model.event.SkipTurnEvent;

import java.util.Map;
import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;


public class SkipTurnProcessorTest {

    private final SkipTurnProcessor skipTurnProcessor = new SkipTurnProcessor();

    @Mock
    private BiConsumer<String, Event> biConsumer;

    @BeforeEach
    private void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void doProcess_setCorrectNextPlayer() {
        //Given
        Player player = new Player("playerId", null, null);
        Game game = new Game(null, Map.of(player.getPlayerId(), player), null,
                null, null, null);
        SkipTurnEvent event = new SkipTurnEvent(123, null, player);

        //When
        skipTurnProcessor.doProcess(event, game, biConsumer);

        //Then
        assertThat(game.getNextPlayer().get().getPlayerId()).isEqualTo(event.getPlayer().getPlayerId());
        EndTurnEvent endTurnEvent = new EndTurnEvent
                (event.getEventId() + 1, EventType.END_TURN, event.getPlayer());
        Mockito.verify(biConsumer).accept(game.getGameId(), endTurnEvent);
    }
}