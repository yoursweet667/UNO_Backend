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
    /**
     * Number of card
     */
    private final Integer number;

    public Card(CardType type, CardColour colour, Integer number) {
        this.type = type;
        this.colour = colour;
        this.number = number;
    }

    public CardType getType() {
        return type;
    }

    public CardColour getColour() {
        return colour;
    }

    public Integer getNumber() {
        return number;
    }
}
