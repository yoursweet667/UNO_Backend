package ru.yoursweet667.uno.service.model;

public class Player {
    /**
     * Id of player
     */
    private final String playerId;
    /**
     * Name of player
     */
    private final String name;


    public Player(String playerId, String name) {
        this.playerId = playerId;
        this.name = name;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }
}
