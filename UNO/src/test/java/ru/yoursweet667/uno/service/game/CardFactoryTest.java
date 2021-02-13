package ru.yoursweet667.uno.service.game;

import org.junit.jupiter.api.Test;
import ru.yoursweet667.uno.service.model.Card;
import ru.yoursweet667.uno.service.model.CardColour;
import ru.yoursweet667.uno.service.model.CardType;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CardFactoryTest {

    @Test
    void createDeck_createsDeck() {

        //When
        List<Card> cards = CardFactory.createDeck();

        //Then
        assertThat(cards).hasSize(108);

        for (CardType cardType : CardType.values()) {

            if (cardType == CardType.PLUS_4 || cardType == CardType.CHANGE_COLOUR) {
                assertContainsNumberOfTimes(cards, new Card(cardType), 4);
            } else {
                for (CardColour cardColour : CardColour.values()) {
                    if ((cardType == CardType.ZERO)) {
                        assertContainsNumberOfTimes(cards, new Card(cardType, cardColour), 1);
                    } else {
                        assertContainsNumberOfTimes(cards, new Card(cardType, cardColour), 2);
                    }

                }
            }
        }
    }

    private void assertContainsNumberOfTimes(List<Card> cards, Card expectedCard, int numberOfExpectedCards) {

        List<Card> expectedCards = Stream.generate(() -> expectedCard)
                .limit(numberOfExpectedCards)
                .collect(Collectors.toList());
        assertThat(cards).containsAll(expectedCards);
    }

    @Test
    void createDeck_oldAndNewDeckDifferent_exception() {

        //When
        List<Card> deckOne = CardFactory.createDeck();
        List<Card> deckTwo = CardFactory.createDeck();

        //Then
        if (deckOne.equals(deckTwo)) {
            assertThat(IllegalArgumentException.class);
        }
    }
}