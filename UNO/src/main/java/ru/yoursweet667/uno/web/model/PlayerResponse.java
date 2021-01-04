package ru.yoursweet667.uno.web.model;

public class PlayerResponse {
    /**
     * Id of player
     */
    private final String playerId;
    /**
     * Name of player
     */
    private final String name;
    /**
     * Indicates whether this player is a current player
     */
    private final boolean currentPlayer;

    public PlayerResponse(String playerId, String name, boolean currentPlayer) {
        this.playerId = playerId;
        this.name = name;
        this.currentPlayer = currentPlayer;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public boolean isCurrentPlayer() {
        return currentPlayer;
    }
}
