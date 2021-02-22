package ru.yoursweet667.uno.service.event.validator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.event.LeaveGameEvent;

import java.util.HashMap;
import java.util.Map;

public class LeaveGameValidatorTest {

    private static final LeaveGameValidator leaveGameValidator = new LeaveGameValidator();

    @Test
    void validator_playerNotFound_ThrowsException() {
        //Given
        Player player = new Player("playerId", null, null);
        Map< String, Player> players = new HashMap<>();
        LeaveGameEvent event = new LeaveGameEvent(123, null, player );
        Game game = new Game(null, players, null, null,
                null, null);

        //When+Then
        Assertions.assertThatCode(() -> leaveGameValidator.validate(event, game))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validator_gameContainsPlayer_NoException() {
        //Given
        Player player = new Player("playerId", null, null);
        Map< String, Player> players = new HashMap<>();
        players.put(player.getPlayerId(), player);
        LeaveGameEvent event = new LeaveGameEvent(123, null, player );
        Game game = new Game(null, players, null, null,
                null, null);

        //When+Then
        Assertions.assertThatCode(() -> leaveGameValidator.validate(event, game))
                .doesNotThrowAnyException();
    }
}
