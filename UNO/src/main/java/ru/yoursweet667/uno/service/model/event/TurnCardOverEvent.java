package ru.yoursweet667.uno.service.model.event;

import ru.yoursweet667.uno.service.model.EventType;
import ru.yoursweet667.uno.service.model.Card;

import java.util.Objects;

public class TurnCardOverEvent extends Event {

    /**
     * First Card
     */
    private final Card card;

    public TurnCardOverEvent(int eventId, EventType type, Card card) {
        super(eventId, type);
        this.card = card;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TurnCardOverEvent that = (TurnCardOverEvent) o;
        return Objects.equals(card, that.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), card);
    }

    @Override
    public String toString() {
        return "TurnCardOverEvent{" +
                "card=" + card +
                ", eventId=" + getEventId() +
                ", type=" + getType() +
                '}';
    }

    public Card getCard() {
        return card;
    }
}