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
            int howMuchTimesCrateCard;
            if (cardType == CardType.ZERO) {
                howMuchTimesCrateCard = 1;
            } else if (cardType == CardType.CHANGE_COLOUR || cardType == CardType.PLUS_4) {
                howMuchTimesCrateCard = 4;
            } else {
                howMuchTimesCrateCard = 2;
            }
            cards.addAll(createCards(howMuchTimesCrateCard, cardType));

        }
        Collections.shuffle(cards);
        return cards;
    }

    private static List<Card> createCards(int howMuchTimesCrateCard, CardType cardType) {
        List<Card> cards = new ArrayList<>();

        for (int i = 1; i <= howMuchTimesCrateCard; i++) {
            Card card = null;
            if (cardType == CardType.CHANGE_COLOUR || cardType == CardType.PLUS_4) {
                card = new Card(cardType);
                cards.add(card);
            } else {
                for (CardColour cardColour : CardColour.values()) {
                    card = new Card(cardType, cardColour);
                    cards.add(card);
                }
            }

        }
        return cards;
    }
}