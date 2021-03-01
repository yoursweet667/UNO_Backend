package ru.yoursweet667.uno.service.event.processor;

import org.junit.jupiter.api.Test;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.event.StartTurnEvent;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class StartTurnProcessorTest {

    StartTurnProcessor startTurnProcessor = new StartTurnProcessor();

    @Test
    void doProcess_setNextPlayer() {

        //Given
        Player player = new Player("playerId", null, null);
        Game game = new Game(null, Map.of(player.getPlayerId(), player), null,
                null, null, null);
        StartTurnEvent event = new StartTurnEvent(123, null, player);

        //When
        startTurnProcessor.doProcess(event, game);

        //Then
        assertThat(game.getNextPlayer().get().getPlayerId()).isEqualTo(event.getPlayer().getPlayerId());

    }

}
