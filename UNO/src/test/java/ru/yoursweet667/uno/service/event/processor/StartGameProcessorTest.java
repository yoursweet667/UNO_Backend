package ru.yoursweet667.uno.service.event.processor;

import org.junit.jupiter.api.Test;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.GameState;
import ru.yoursweet667.uno.service.model.Player;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class StartGameProcessorTest {

    StartGameProcessor startGameProcessor = new StartGameProcessor();

    @Test
    void doProcess_setNextPlayerAndSetGameState() {

        //Given
        Player player = new Player("playerId", null, null);
        Game game = new Game(null, Map.of(player.getPlayerId(), player), null,
                null, null, null);

        //When
        startGameProcessor.doProcess(null, game);

        //Then
        assertThat(game.getGameState()).isEqualTo(GameState.START_TURN);
        assertThat(game.getNextPlayer()).isPresent();

    }

}
