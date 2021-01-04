package ru.yoursweet667.uno.service.model;

import ru.yoursweet667.uno.web.model.Card;

public class FirstCardTurnedUpEvent extends Event {


    /**
     * Game State
     */
    private final GameState gameState;
    /**
     * First Card
     */
    private final Card card;

    public FirstCardTurnedUpEvent(Integer eventId, EventType type, GameState gameState, Card card) {
        super(eventId, type);
        this.gameState = gameState;
        this.card = card;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Card getCard() {
        return card;
    }
}
