package ru.yoursweet667.uno.service.model.event;

import ru.yoursweet667.uno.service.model.Card;
import ru.yoursweet667.uno.service.model.EventType;

public class PlayCardEvent extends Event {

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
    private final Card card;

    public PlayCardEvent(Integer eventId, String sourcePlayerId, String targetPlayerId, Card card) {
        super(eventId, EventType.PLAY_CARD);
        this.sourcePlayerId = sourcePlayerId;
        this.targetPlayerId = targetPlayerId;
        this.card = card;
    }

    public String getSourcePlayerId() {
        return sourcePlayerId;
    }

    public String getTargetPlayerId() {
        return targetPlayerId;
    }

    public Card getCard() {
        return card;
    }
}
