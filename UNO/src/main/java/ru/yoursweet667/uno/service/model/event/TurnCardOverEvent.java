package ru.yoursweet667.uno.service.model.event;

import ru.yoursweet667.uno.service.model.EventType;
import ru.yoursweet667.uno.service.model.Card;

public class TurnCardOverEvent extends Event {

    /**
     * First Card
     */
    private final Card card;

    public TurnCardOverEvent(Integer eventId, EventType type, Card card) {
        super(eventId, type);
        this.card = card;
    }

    @Override
    public String toString() {
        return "TurnCardOverEvent{" +
                "card=" + card +
                '}';
    }

    public Card getCard() {
        return card;
    }
}