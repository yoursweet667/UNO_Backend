package ru.yoursweet667.uno.service.event.processor;

import org.junit.jupiter.api.Test;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.event.JoinGameEvent;

import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;


public class JoinGameProcessorTest {

    JoinGameProcessor joinGameProcessor = new JoinGameProcessor();

    @Test
    void doProcess_joinPlayer() {

        //Given
        Map<String, Player> players = new HashMap<>();
        Game game = new Game(null, players, null,
                null, null, null);

        Player player = new Player("playerId", null, null);
        JoinGameEvent event = new JoinGameEvent(123, null, player);

        //When
        joinGameProcessor.doProcess(event, game);

        //Then
        assertThat(game.getPlayers().get("playerId")).isEqualTo(player);
    }
}
