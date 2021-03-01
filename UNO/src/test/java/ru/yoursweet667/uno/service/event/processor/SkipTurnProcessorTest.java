package ru.yoursweet667.uno.service.event.processor;

import org.junit.jupiter.api.Test;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.event.SkipTurnEvent;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class SkipTurnProcessorTest {

    SkipTurnProcessor skipTurnProcessor = new SkipTurnProcessor();

    @Test
    void doProcess_setRightNextPlayer() {

        //Given
        Player player = new Player("playerId", null, null);
        Game game = new Game(null, Map.of(player.getPlayerId(), player), null,
                null, null, null);
        SkipTurnEvent event = new SkipTurnEvent(123, null, player);

        //When
        skipTurnProcessor.doProcess(event, game);

        //Then
        assertThat(game.getNextPlayer().get().getPlayerId()).isEqualTo(event.getPlayer().getPlayerId());
    }
}