package ru.yoursweet667.uno.service.model;

public class Card {
    /**
     * Type of card
     */
    private final CardType type;
    /**
     * Colour of card
     */
    private final CardColour colour;

    public Card(CardType type, CardColour colour) {
        this.type = type;
        this.colour = colour;
    }

    public CardType getType() {
        return type;
    }

    public CardColour getColour() {
        return colour;
    }
}
