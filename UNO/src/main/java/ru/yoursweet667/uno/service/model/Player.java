package ru.yoursweet667.uno.service.model;

import java.util.List;

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