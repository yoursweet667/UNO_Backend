package ru.yoursweet667.uno.service.event.processor;

import org.junit.jupiter.api.Test;
import ru.yoursweet667.uno.service.model.*;
import ru.yoursweet667.uno.service.model.event.Event;
import ru.yoursweet667.uno.service.model.event.TurnCardOverEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;

public class TurnCardOverProcessorTest {

    private final TurnCardOverProcessor turnCardOverProcessor = new TurnCardOverProcessor();

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
        ValueHoldingBiConsumer resultEventConsumer = new ValueHoldingBiConsumer();
        turnCardOverProcessor.doProcess(event, game, resultEventConsumer);

        //Then
        assertThat(game.getCardsInTheGame()).contains(event.getCard());
        assertThat(resultEventConsumer.gameId).isEqualTo(game.getGameId());
        assertThat(resultEventConsumer.event.getType()).isEqualTo(EventType.START_TURN);
    }

    private static class ValueHoldingBiConsumer implements BiConsumer<String, Event> {

        private Event event;
        private String gameId;

        public void accept(String gameId, Event event) {
            this.gameId = gameId;
            this.event = event;
        }
    }
}
