package ru.yoursweet667.uno.service.event.processor;

import net.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.Test;
import ru.yoursweet667.uno.service.model.*;
import ru.yoursweet667.uno.service.model.event.EndTurnEvent;
import ru.yoursweet667.uno.service.model.event.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;


public class EndTurnProcessorTest {

    private static final EndTurnProcessor endTurnProcessor = new EndTurnProcessor();

    @Test
    void doProcess_changeGameStateAndNextPlayer_startTurn() {

        //Given
        Player player = new Player("playerId", null, null);
        List<Card> cards = new ArrayList<>();
        Card card = new Card(null, null, true);

        int i = 0;
        do {cards.add(card); i++; }
        while (i < 3);

        Player playerForEvent = new Player("playerIdFromEvent", null, cards);
        Game game = new Game(null, Map.of(player.getPlayerId(), player), GameState.START_TURN,
                null, null, null);
        EndTurnEvent event = new EndTurnEvent(123, null, playerForEvent);

        ValueHoldingBiConsumer resultEventConsumer = new ValueHoldingBiConsumer();

        //When
        endTurnProcessor.doProcess(event, game, resultEventConsumer);

        //Then
        assertThat(game.getGameState()).isEqualTo(GameState.END_TURN);
        assertThat(game.getNextPlayer().get().getPlayerId()).isEqualTo("playerId");
        assertThat(resultEventConsumer.event.getType()).isEqualTo(EventType.START_TURN);
        assertThat(resultEventConsumer.gameId).isEqualTo(game.getGameId());
    }

    private static class ValueHoldingBiConsumer implements BiConsumer<String, Event> {

        private Event event;
        private String gameId;

        public void accept(String gameId, Event event) {
            this.gameId = gameId;
            this.event = event;
        }
    }

    @Test
    void doProcess_changeGameStateAndNextPlayer_endTurn() {

        //Given
        Player player = new Player("playerId", null, null);
        List<Card> cards = new ArrayList<>();

        Player playerForEvent = new Player("playerIdFromEvent", null, cards);
        Game game = new Game(null, Map.of(player.getPlayerId(), player), GameState.START_TURN,
                null, null, null);
        EndTurnEvent event = new EndTurnEvent(123, null, playerForEvent);

        ValueHoldingBiConsumer resultEventConsumer = new ValueHoldingBiConsumer();

        //When
        endTurnProcessor.doProcess(event, game, resultEventConsumer);

        //Then
        assertThat(game.getGameState()).isEqualTo(GameState.END_TURN);
        assertThat(game.getNextPlayer().get().getPlayerId()).isEqualTo("playerId");
        assertThat(resultEventConsumer.event.getType()).isEqualTo(EventType.END_GAME);
        assertThat(resultEventConsumer.gameId).isEqualTo(game.getGameId());
    }

}
