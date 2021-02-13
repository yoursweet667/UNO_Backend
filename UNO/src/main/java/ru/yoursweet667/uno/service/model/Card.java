package ru.yoursweet667.uno.service.model;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return type == card.type &&
                colour == card.colour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, colour);
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
