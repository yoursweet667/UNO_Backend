package ru.yoursweet667.uno.service.event.processor;

import org.junit.jupiter.api.Test;
import ru.yoursweet667.uno.service.model.EventType;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.event.Event;
import ru.yoursweet667.uno.service.model.event.JoinGameEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;


public class JoinGameProcessorTest {

    private final JoinGameProcessor joinGameProcessor = new JoinGameProcessor();

    @Test
    void doProcess_joinPlayer() {

        //Given
        int i = 0;
        Map<String, Player> players = new HashMap<>();
        Player playerForGame = new Player("somePlayerId", null, null);
        Player player = new Player("playerId", null, null);
        players.put(playerForGame.getPlayerId(), playerForGame);
        Game game = new Game(null, players, null,
                null, null, null);

        JoinGameEvent event = new JoinGameEvent(123, EventType.JOIN_GAME, player);

        ValueHoldingBiConsumer resultEventConsumer = new ValueHoldingBiConsumer();

        //When
        joinGameProcessor.doProcess(event, game, resultEventConsumer);

        //Then
        assertThat(game.getPlayers().get("playerId")).isEqualTo(player);
        assertThat(resultEventConsumer.event.getType()).isEqualTo(EventType.START_GAME);
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
}

