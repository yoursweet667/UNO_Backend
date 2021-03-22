package ru.yoursweet667.uno.service.event.processor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.yoursweet667.uno.service.model.*;
import ru.yoursweet667.uno.service.model.event.Event;
import ru.yoursweet667.uno.service.model.event.StartTurnEvent;
import ru.yoursweet667.uno.service.model.event.TurnCardOverEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;

public class TurnCardOverProcessorTest {

    private final TurnCardOverProcessor turnCardOverProcessor = new TurnCardOverProcessor();

    @Mock
    private BiConsumer<String, Event> biConsumer;

    @BeforeEach
    private void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void doProcess_addCardInGame() {
        //Given
        Player nextPlayerInGame = new Player(null, null, null);
        List<Card> cardsInTheGame = new ArrayList<>();
        Card card = new Card(CardType.FIVE, null);
        Game game = new Game(null, null, null,
                null, cardsInTheGame, null);
        TurnCardOverEvent event = new TurnCardOverEvent(123, null, card);
        game.setNextPlayer(nextPlayerInGame);

        //When
        turnCardOverProcessor.doProcess(event, game, biConsumer);

        //Then
        assertThat(game.getCardsInTheGame()).contains(event.getCard());
        StartTurnEvent startTurnEvent = new StartTurnEvent
                (event.getEventId() + 1, EventType.START_TURN, game.getNextPlayer().get());
        Mockito.verify(biConsumer).accept(game.getGameId(), startTurnEvent);
    }
}
