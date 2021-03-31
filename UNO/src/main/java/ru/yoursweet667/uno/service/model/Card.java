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
    /**
     *
     */
    private final boolean visible;


    public Card(CardType type, CardColour colour, boolean visible) {
        this.type = type;
        this.colour = colour;
        this.visible = visible;
    }

    public Card(CardType type, CardColour colour) {
        this.type = type;
        this.colour = colour;
        this.visible = true;
    }

    public Card(CardType type) {
        this.type = type;
        this.colour = null;
        this.visible = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return visible == card.visible &&
                type == card.type &&
                colour == card.colour;
    }

    @Override
    public String toString() {
        return "Card{" +
                "type=" + type +
                ", colour=" + colour +
                ", visible=" + visible +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, colour, visible);
    }

    public CardType getType() {
        return type;
    }

    public CardColour getColour() {
        return colour;
    }

    public boolean getVisible() {
        return visible;
    }
}
