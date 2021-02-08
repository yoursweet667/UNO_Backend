package ru.yoursweet667.uno.service.game;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import ru.yoursweet667.uno.service.model.Card;
import ru.yoursweet667.uno.service.model.CardColour;
import ru.yoursweet667.uno.service.model.CardType;

import java.util.List;

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
                assertThat(cards).haveExactly(4,
                        new Condition<>(new Card(cardType)::equals, "equals"));
            } else {
                for (CardColour cardColour : CardColour.values()) {
                    if ((cardType == CardType.ZERO)) {
                        assertThat(cards).haveExactly(1,
                                new Condition<>(new Card(cardType, cardColour)::equals, "equals"));
                    } else {
                        assertThat(cards).haveExactly(2,
                                new Condition<>(new Card(cardType, cardColour)::equals, "equals"));
                    }

                }
            }
        }
    }
}