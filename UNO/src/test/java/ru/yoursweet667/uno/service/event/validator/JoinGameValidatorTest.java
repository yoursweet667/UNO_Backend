package ru.yoursweet667.uno.service.event.validator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.event.JoinGameEvent;

import java.util.HashMap;
import java.util.Map;

public class JoinGameValidatorTest {

    private final JoinGameValidator joinGameValidator = new JoinGameValidator();

    @Test
    void validate_gameAlreadyContainsPlayer_throwsException() {
     
        //Given
        String playerId = "somePlayerId";
        Player player = new Player(playerId, null, null);
        Game game = new Game(null, Map.of(playerId, player), null,
                null, null, null);
        JoinGameEvent event = new JoinGameEvent(123, null, player);

        //When+Then
        Assertions.assertThatThrownBy(() -> joinGameValidator.validate(event, game))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void validate_gameDoNotContainsPlayer_noException() {
        //Given

        Map<String, Player> listOfPlayers = new HashMap<>();
        String playerId = "somePlayerId";
        Player player = new Player(playerId, null, null);
        Game game = new Game(null, listOfPlayers, null,
                null, null, null);
        JoinGameEvent event = new JoinGameEvent(123, null, player);

        //When+Then
        Assertions.assertThatCode(() -> joinGameValidator.validate(event, game))
                .doesNotThrowAnyException();
    }
}