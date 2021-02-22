package ru.yoursweet667.uno.service.model;

import java.util.List;
import java.util.Objects;

public class Player {
    /**
     * Id of player
     */
    private final String playerId;
    /**
     * Name of player
     */
    private final String name;
    /**
     * Player cards
     */
    private final List<Card> cards;

    public Player(String playerId, String name, List<Card> cards) {
        this.playerId = playerId;
        this.name = name;
        this.cards = cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(playerId, player.playerId) &&
                Objects.equals(name, player.name) &&
                Objects.equals(cards, player.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, name, cards);
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId='" + playerId + '\'' +
                ", name='" + name + '\'' +
                ", cards=" + cards +
                '}';
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}