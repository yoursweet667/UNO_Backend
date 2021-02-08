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

    public Card(CardType type) {
        this.type = type;
        this.colour = null;
    }

    @Override
    public String toString() {
        return "Card{" +
                "type=" + type +
                ", colour=" + colour +
                '}';
    }

    public CardType getType() {
        return type;
    }

    public CardColour getColour() {
        return colour;
    }

}
