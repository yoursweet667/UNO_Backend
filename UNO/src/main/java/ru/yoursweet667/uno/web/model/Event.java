package ru.yoursweet667.uno.web.model;

import java.util.List;

public class Event {
    /**
     * Id of event
     */
    private final Integer eventId;
    /**
     * Type of event
     */
    private final EventType type;
    /**
     * Source player id
     */
    private final String sourcePlayerId;
    /**
     * Target player id
     */
    private final String targetPlayerId;
    /**
     * List of cards
     */
    private final List<Card> cards;

    public Event(Integer eventId, EventType type, String sourcePlayerId, String targetPlayerId, List<Card> cards) {
        this.eventId = eventId;
        this.type = type;
        this.sourcePlayerId = sourcePlayerId;
        this.targetPlayerId = targetPlayerId;
        this.cards = cards;
    }

    public Integer getEventId() {
        return eventId;
    }

    public EventType getType() {
        return type;
    }

    public String getSourcePlayerId() {
        return sourcePlayerId;
    }

    public String getTargetPlayerId() {
        return targetPlayerId;
    }

    public List<Card> getCards() {
        return cards;
    }
}
