package ru.yoursweet667.uno.service.game;

import ru.yoursweet667.uno.service.model.Card;
import ru.yoursweet667.uno.service.model.CardColour;
import ru.yoursweet667.uno.service.model.CardType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardFactory {

    public static List<Card> createDeck() {

        List<Card> cards = new ArrayList<>();
        for (CardType cardType : CardType.values()) {
            int numberOfCard;
            if (cardType == CardType.ZERO) {
                numberOfCard = 1;
            } else if (cardType == CardType.CHANGE_COLOUR || cardType == CardType.PLUS_4) {
                numberOfCard = 4;
            } else {
                numberOfCard = 2;
            }
            cards.addAll(createCards(numberOfCard, cardType));

        }
        Collections.shuffle(cards);
        return cards;
    }

    private static List<Card> createCards(int numberOfCards, CardType cardType) {
        List<Card> cards = new ArrayList<>();

        for (int i = 1; i <= numberOfCards; i++) {
            Card card = null;
            if (cardType == CardType.CHANGE_COLOUR || cardType == CardType.PLUS_4) {
                card = new Card(cardType);
            } else {
                for (CardColour cardColour : CardColour.values()) {
                    card = new Card(cardType, cardColour);
                }
            }
            cards.add(card);
        }
        return cards;
    }
}