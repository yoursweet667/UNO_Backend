package ru.yoursweet667.uno.service.event.processor;

import org.junit.jupiter.api.Test;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.GameState;

import static org.assertj.core.api.Assertions.assertThat;

public class EndGameProcessorTest {

    private static final EndGameProcessor endGameProcessor = new EndGameProcessor();

    @Test
    void doProcess_changeGameState() {
        //Given
        Game game = new Game(null, null, GameState.START_TURN,
                null, null, null);

        //When
        endGameProcessor.doProcess(null, game, null);

        //Then
        assertThat(game.getGameState()).isEqualTo(GameState.GAME_ENDED);
    }
}
