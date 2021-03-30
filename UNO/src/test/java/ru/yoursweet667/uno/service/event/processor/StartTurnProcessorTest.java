package ru.yoursweet667.uno.service.event.processor;

import org.junit.jupiter.api.Test;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.event.StartTurnEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class StartTurnProcessorTest {

    private final StartTurnProcessor startTurnProcessor = new StartTurnProcessor();

    @Test
    void doProcess_setNextPlayer() {
        //Given
        Player firstPlayer = new Player("firstPlayerId", null, null);
        Player secondPlayer = new Player("secondPlayerId", null, null);
        Map<String, Player> players = new HashMap<>();
        players.put(firstPlayer.getPlayerId(), firstPlayer);
        players.put(secondPlayer.getPlayerId(), secondPlayer);
        Game game = new Game(null, players, null,
                null, null, null);
        StartTurnEvent event = new StartTurnEvent(123, null, firstPlayer);

        //When
        startTurnProcessor.doProcess(event, game, null);

        //Then
        assertThat(game.getPlayers().size()).isEqualTo(2);
        Optional<Player> nextPlayer = game.getNextPlayer();
        assertThat(nextPlayer).isPresent();
        assertThat(nextPlayer.get().getPlayerId()).isEqualTo(event.getPlayer().getPlayerId());
    }
}