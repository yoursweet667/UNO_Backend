package ru.yoursweet667.uno.service.event.processor;

import org.junit.jupiter.api.Test;
import ru.yoursweet667.uno.service.model.EventType;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.event.Event;
import ru.yoursweet667.uno.service.model.event.SkipTurnEvent;

import java.util.Map;
import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;


public class SkipTurnProcessorTest {

    private final SkipTurnProcessor skipTurnProcessor = new SkipTurnProcessor();

    @Test
    void doProcess_setCorrectNextPlayer() {

        //Given
        Player player = new Player("playerId", null, null);
        Game game = new Game(null, Map.of(player.getPlayerId(), player), null,
                null, null, null);
        SkipTurnEvent event = new SkipTurnEvent(123, null, player);

        //When

        ValueHoldingBiConsumer resultEventConsumer = new ValueHoldingBiConsumer();
        skipTurnProcessor.doProcess(event, game, resultEventConsumer);

        //Then
        assertThat(game.getNextPlayer().get().getPlayerId()).isEqualTo(event.getPlayer().getPlayerId());
        assertThat(resultEventConsumer.gameId).isEqualTo(game.getGameId());
        assertThat(resultEventConsumer.event.getType()).isEqualTo(EventType.END_TURN);

    }

    private static class ValueHoldingBiConsumer implements BiConsumer<String, Event> {

        private Event event;
        private String gameId;

        public void accept(String gameId, Event event) {
            this.gameId = gameId;
            this.event = event;
        }
    }
}