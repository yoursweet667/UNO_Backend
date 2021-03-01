package ru.yoursweet667.uno.service.event.processor;

import org.junit.jupiter.api.Test;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.event.LeaveGameEvent;

import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;


public class LeaveGameProcessorTest {

    LeaveGameProcessor leaveGameProcessor = new LeaveGameProcessor();

    @Test
    void doProcess_gameContainsPlayer_removePlayerFromGame(){

        //Given
        Map<String, Player> players = new HashMap();
        Player player = new Player("playerId", null, null);
        players.put(player.getPlayerId(), player);
        LeaveGameEvent event = new LeaveGameEvent(123, null, player);
        Game game = new Game(null, players, null, null,
                null, null);

        //When
        leaveGameProcessor.doProcess(event, game);

        //Then
        assertThat(game.getPlayers()).doesNotContainValue(player);


    }
}
