package ru.yoursweet667.uno.service.model.event;

import ru.yoursweet667.uno.service.model.Card;
import ru.yoursweet667.uno.service.model.EventType;

import java.util.Objects;

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

    public PlayCardEvent(int eventId, String sourcePlayerId, String targetPlayerId, Card card) {
        super(eventId, EventType.PLAY_CARD);
        this.sourcePlayerId = sourcePlayerId;
        this.targetPlayerId = targetPlayerId;
        this.card = card;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PlayCardEvent that = (PlayCardEvent) o;
        return Objects.equals(sourcePlayerId, that.sourcePlayerId) &&
                Objects.equals(targetPlayerId, that.targetPlayerId) &&
                Objects.equals(card, that.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sourcePlayerId, targetPlayerId, card);
    }

    @Override
    public String toString() {
        return "PlayCardEvent{" +
                "sourcePlayerId='" + sourcePlayerId + '\'' +
                ", targetPlayerId='" + targetPlayerId + '\'' +
                ", card=" + card +
                '}';
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