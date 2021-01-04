package ru.yoursweet667.uno.service.model;

import ru.yoursweet667.uno.web.model.Card;

public class PlayerPlayedACardEvent extends Event {

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

    public PlayerPlayedACardEvent(Integer eventId, String sourcePlayerId, String targetPlayerId, Card card) {
        super(eventId, EventType.PLAYER_PLAYED_A_CARD);
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
