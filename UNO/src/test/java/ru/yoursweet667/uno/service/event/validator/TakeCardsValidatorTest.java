package ru.yoursweet667.uno.service.event.validator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.yoursweet667.uno.service.model.*;
import ru.yoursweet667.uno.service.model.event.TakeCardsEvent;

import java.util.ArrayList;
import java.util.List;

public class TakeCardsValidatorTest {

    private static final TakeCardsValidator takeCardsValidator = new TakeCardsValidator();

    @Test
    void validate_initialising_noException() {

        //Given
        Game game = new Game(null, null, GameState.INITIALISING,
                null, null, null);
        Player player = new Player(null, null, List.of());
        List<Card> cardsInEvent = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            Card card = new Card(null, null);
            cardsInEvent.add(card);
        }
        TakeCardsEvent event = new TakeCardsEvent(123, null, cardsInEvent, player);

        //When+Then
        Assertions.assertThatCode(() -> takeCardsValidator.validate(event, game))
                .doesNotThrowAnyException();

    }

    @Test
    void validate_playerAlreadyHasCards_exception() {

        //Given
        Card playerCard = new Card(CardType.FIVE, CardColour.BLUE);
        Game game = new Game(null, null, GameState.INITIALISING,
                null, null, null);
        Player player = new Player(null, null, List.of(playerCard));
        List<Card> cardsInEvent = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            Card card = new Card(null, null);
            cardsInEvent.add(card);
        }
        TakeCardsEvent event = new TakeCardsEvent(123, null, cardsInEvent, player);

        //When+Then
        Assertions.assertThatThrownBy(() -> takeCardsValidator.validate(event, game))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @ParameterizedTest
    @ValueSource(ints = { 6, 8 })

    void validate_eventHasDifferentCardFormSeven_exception(int cards) {


        //Given
        Game game = new Game(null, null, GameState.INITIALISING,
                null, null, null);
        Player player = new Player(null, null, List.of());
        List<Card> cardsInEvent = new ArrayList<>();
        for (int i = 1; i <= cards; i++) {
            Card card = new Card(null, null);
            cardsInEvent.add(card);
        }
        TakeCardsEvent event = new TakeCardsEvent(123, null, cardsInEvent, player);

        //When+Then
        Assertions.assertThatThrownBy(() -> takeCardsValidator.validate(event, game))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void validate_startTurn_noException() {
        //Given
        Card lasCardInTheGame = new Card(CardType.FIVE, CardColour.BLUE);
        Player nextPlayer = new Player("nextPlayerId", null, List.of());
        Game game = new Game(null, null, GameState.START_TURN, null, List.of(lasCardInTheGame),
                null);
        game.getLastCardInTheGame();
        game.setNextPlayer(nextPlayer);
        Card card = new Card(CardType.FIVE, CardColour.RED);
        Player player = new Player("playerId", null, List.of(card));
        TakeCardsEvent event = new TakeCardsEvent(123, null, null, player);

        //When+Then
        Assertions.assertThatCode(() -> takeCardsValidator.validate(event, game))
                .doesNotThrowAnyException();
    }

    @Test
    void validate_nextPlayerEqualsCurrentPlayer_exception() {
        //Given
        Card lasCardInTheGame = new Card(CardType.FIVE, CardColour.BLUE);
        Player nextPlayer = new Player("playerId", null, List.of());
        Game game = new Game(null, null, GameState.START_TURN, null, List.of(lasCardInTheGame),
                null);
        game.getLastCardInTheGame();
        game.setNextPlayer(nextPlayer);
        Player player = new Player("playerId", null, List.of());
        TakeCardsEvent event = new TakeCardsEvent(123, null, null, player);

        //When+Then
        Assertions.assertThatThrownBy(() -> takeCardsValidator.validate(event, game))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validate_playerDoesNotHaveCardToPlay_exception() {
        //Given
        Card lasCardInTheGame = new Card(CardType.FIVE, CardColour.BLUE);
        Player nextPlayer = new Player("nextPlayerId", null, List.of());
        Game game = new Game(null, null, GameState.START_TURN, null, List.of(lasCardInTheGame),
                null);
        game.getLastCardInTheGame();
        game.setNextPlayer(nextPlayer);
        Card card = new Card(CardType.ZERO, CardColour.RED);
        Player player = new Player("playerId", null, List.of(card));
        TakeCardsEvent event = new TakeCardsEvent(123, null, null, player);

        //When+Then
        Assertions.assertThatThrownBy(() -> takeCardsValidator.validate(event, game))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void validate_incorrectGameState_exception() {
        //Given
        Game game = new Game(null, null, GameState.GAME_ENDED,
                null, null, null);

        //When+Then
        Assertions.assertThatThrownBy(() -> takeCardsValidator.validate(null, game))
                .isInstanceOf(IllegalArgumentException.class);
    }
}