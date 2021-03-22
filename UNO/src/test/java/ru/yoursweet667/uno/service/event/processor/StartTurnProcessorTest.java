package ru.yoursweet667.uno.service.event.processor;

import org.junit.jupiter.api.Test;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.event.StartTurnEvent;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class StartTurnProcessorTest {

    private final StartTurnProcessor startTurnProcessor = new StartTurnProcessor();

    @Test
    void doProcess_setNextPlayer() {
        //Given
        Player player = new Player("playerId", null, null);
        Game game = new Game(null, Map.of(player.getPlayerId(), player), null,
                null, null, null);
        StartTurnEvent event = new StartTurnEvent(123, null, player);
        Optional<Player> nextPlayer = game.getNextPlayer();

        //When
        startTurnProcessor.doProcess(event, game, null);

        //Then
        assertThat(nextPlayer).isPresent();
        assertThat(nextPlayer.get().getPlayerId()).isEqualTo(event.getPlayer().getPlayerId());
    }
}