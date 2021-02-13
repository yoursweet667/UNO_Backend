package ru.yoursweet667.uno.service.model.event;

import ru.yoursweet667.uno.service.model.Card;
import ru.yoursweet667.uno.service.model.EventType;
import ru.yoursweet667.uno.service.model.Player;

import java.util.List;
import java.util.Objects;

public class TakeCardsEvent extends Event {

    /**
     * Card
     */
    private final List<Card> cards;
    /**
     * Source player id
     */
    private final Player player;

    public TakeCardsEvent(int eventId, EventType type, List<Card> cards, Player player) {
        super(eventId, type);
        this.cards = cards;
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TakeCardsEvent that = (TakeCardsEvent) o;
        return Objects.equals(cards, that.cards) &&
                Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cards, player);
    }

    @Override
    public String toString() {
        return "TakeCardsEvent{" +
                "cards=" + cards +
                ", player=" + player +
                '}';
    }

    public List<Card> getCards() {
        return cards;
    }

    public Player getPlayer() {
        return player;
    }
}
