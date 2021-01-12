package ru.yoursweet667.uno.service.model.event;

import ru.yoursweet667.uno.service.model.Card;
import ru.yoursweet667.uno.service.model.EventType;
import ru.yoursweet667.uno.service.model.Player;

import java.util.List;

public class TakeCardsEvent extends Event {

    /**
     * Card
     */
    private final List<Card> cards;
    /**
     * Source player id
     */
    private final Player player;

    public TakeCardsEvent(Integer eventId, EventType type, List<Card> cards, Player player) {
        super(eventId, type);
        this.cards = cards;
        this.player = player;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Player getPlayer() {
        return player;
    }
}
