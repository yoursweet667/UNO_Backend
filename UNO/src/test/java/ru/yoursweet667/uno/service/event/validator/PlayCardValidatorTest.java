package ru.yoursweet667.uno.service.event.validator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yoursweet667.uno.service.model.*;
import ru.yoursweet667.uno.service.model.event.PlayCardEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayCardValidatorTest {

    private static final PlayCardValidator playCardValidator = new PlayCardValidator();

    @Test
    void validate_itIsNotSourcePlayerTurn_exception() {
        //Given
        String playerId = "playerId";
        Card card = new Card(CardType.ZERO, CardColour.RED);
        Player nextPlayer = new Player(playerId, null, List.of(card));
        Map<String, Player> players = Map.of(playerId, nextPlayer);
        PlayCardEvent event = new PlayCardEvent(123, "sourcePlayerId", null, card);
        Game game = new Game(null, players, null, null,
                null, null);
        game.setNextPlayer(nextPlayer);

        //When+Then
        Assertions.assertThatThrownBy(() -> playCardValidator.validate(event, game))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void validate_thereIsNoPlayer_exception() {
        //Given
        PlayCardEvent event = new PlayCardEvent(123, "sourcePlayerId", null, null);
        Game game = new Game(null, Map.of(), null, null,
                null, null);


        //When+Then
        Assertions.assertThatThrownBy(() -> playCardValidator.validate(event, game))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void validate_SourcePlayerTurn_noException() {
        //Given
        Card card = new Card(CardType.ZERO, CardColour.RED);
        String playerId = "playerId";
        Player player = new Player(playerId, null, List.of(card));
        Map<String, Player> players = Map.of(playerId, player);
        PlayCardEvent event = new PlayCardEvent(123, "playerId", null, card);
        Game game = new Game(null, players, null, null,
                null, null);
        game.setNextPlayer(player);

        //When+Then
        Assertions.assertThatCode(() -> playCardValidator.validate(event, game))
                .doesNotThrowAnyException();
    }

}