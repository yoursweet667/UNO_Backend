package ru.yoursweet667.uno.service.model;

import java.util.List;

public class PlayerTakesInitialCardsEvent extends Event {

    /**
     * Player Id Who Takes Initial Cards
     */
    private final String playerId;
    /**
     * Array of Initial Cards
     */
    private List<Card> cards;

    public PlayerTakesInitialCardsEvent(Integer eventId, EventType type, String playerId, List<Card> cards) {
        super(eventId, type);
        this.playerId = playerId;
        this.cards = cards;
    }

    public String getPlayerId() {
        return playerId;
    }

    public List<Card> getCards() {
        return cards;
    }
}