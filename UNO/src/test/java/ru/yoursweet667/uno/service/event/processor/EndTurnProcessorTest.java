package ru.yoursweet667.uno.service.event.processor;

import org.junit.jupiter.api.Test;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.GameState;
import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.event.EndTurnEvent;

import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;


public class EndTurnProcessorTest {

    private static final EndTurnProcessor endTurnProcessor = new EndTurnProcessor();

    @Test
    void doProcess_changeGameStateAndNextPlayer() {

        //Given
        Player player = new Player("playerId", null, null);
        Player playerForEvent = new Player("playerIdFromEvent", null, null);
        Game game = new Game(null, Map.of(player.getPlayerId(), player), GameState.START_TURN,
                null, null, null);
        EndTurnEvent event = new EndTurnEvent(123, null, playerForEvent);

        //When
        endTurnProcessor.doProcess(event, game);

        //Then
        assertThat(game.getGameState()).isEqualTo(GameState.END_TURN);
        assertThat(game.getNextPlayer().get().getPlayerId()).isEqualTo("playerId");
    }
}
