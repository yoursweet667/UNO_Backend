package ru.yoursweet667.uno.service.event.processor;

import org.junit.jupiter.api.Test;
import ru.yoursweet667.uno.service.model.Card;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.GameState;
import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.event.TakeCardsEvent;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TakeCardsProcessorTest {

    private final TakeCardsProcessor takeCardsProcessor = new TakeCardsProcessor();

    @Test
    void doProcess_gameStateInitialising_takeCards() {
        //Given
        Card card = new Card(null, null);
        List<Card> playerCards = new ArrayList<>();
        Player player = new Player(null, null, playerCards);
        Game game = new Game(null, null, GameState.INITIALISING,
                null, null, null);
        TakeCardsEvent event = new TakeCardsEvent(123, null, List.of(card), player);

        //When
        takeCardsProcessor.doProcess(event, game, null);

        //Then
        assertThat(event.getPlayer().getCards()).contains(card);
    }

    @Test
    void doProcess_gameStateStartTurn_takeCards() {
        //Given
        Card card = new Card(null, null);
        List<Card> playerCards = new ArrayList<>();
        Player player = new Player(null, null, playerCards);
        Game game = new Game(null, null, GameState.START_TURN,
                null, null, null);
        TakeCardsEvent event = new TakeCardsEvent(123, null, List.of(card), player);

        //When
        takeCardsProcessor.doProcess(event, game, null);

        //Then
        assertThat(event.getPlayer().getCards()).contains(card);
    }
}
