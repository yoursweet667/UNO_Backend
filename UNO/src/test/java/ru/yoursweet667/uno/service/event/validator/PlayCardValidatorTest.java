package ru.yoursweet667.uno.service.event.validator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.event.PlayCardEvent;

import java.util.HashMap;
import java.util.Map;

public class PlayCardValidatorTest {

    private static final PlayCardValidator playCardValidator = new PlayCardValidator();

    @Test
    void validate_itIsNotSourcePlayerTurn_exception() {
        //Given
        String playerId = "playerId";
        Player player = new Player(playerId, null, null);
        Map<String, Player> players = new HashMap<>();
        players.put(playerId, player);
        PlayCardEvent event = new PlayCardEvent(123, "sourcePlayerId", null, null);
        Game game = new Game(null, players, null, null,
                null, null);

        //When+Then
        Assertions.assertThatThrownBy(() -> playCardValidator.validate(event, game))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void validate_thereIsNoPlayer_exception() {
        //Given
        Map<String, Player> players = new HashMap<>();
        players.put(null, null);
        PlayCardEvent event = new PlayCardEvent(123, "sourcePlayerId", null, null);
        Game game = new Game(null, players, null, null,
                null, null);

        //When+Then
        Assertions.assertThatThrownBy(() -> playCardValidator.validate(event, game))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validate_SourcePlayerTurn_noException() {
        //Given
        String playerId = "playerId";
        Player player = new Player(playerId, null, null);
        Map<String, Player> players = new HashMap<>();
        players.put(playerId, player);
        PlayCardEvent event = new PlayCardEvent(123, "playerId", null, null);
        Game game = new Game(null, null, null, null,
                null, null);
        game.setNextPlayer(player);

        //When+Then
        Assertions.assertThatCode(() -> playCardValidator.validate(event, game))
                .doesNotThrowAnyException();
    }
}
