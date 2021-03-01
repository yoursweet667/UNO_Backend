package ru.yoursweet667.uno.service.event.processor;

import org.junit.jupiter.api.Test;
import ru.yoursweet667.uno.service.model.Card;
import ru.yoursweet667.uno.service.model.CardType;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.event.TurnCardOverEvent;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TurnCardOverProcessorTest {

    TurnCardOverProcessor turnCardOverProcessor = new TurnCardOverProcessor();

    @Test
    void doProcess_addCardInGame() {

        //Given
        List<Card> cardsInTheGame = new ArrayList<>();
        Card card = new Card(CardType.FIVE, null);
        Game game = new Game(null, null, null,
                null, cardsInTheGame, null);
        TurnCardOverEvent event = new TurnCardOverEvent(123, null, card);

        //When
        turnCardOverProcessor.doProcess(event, game);

        //Then
        assertThat(game.getCardsInTheGame()).contains(event.getCard());
    }
}
