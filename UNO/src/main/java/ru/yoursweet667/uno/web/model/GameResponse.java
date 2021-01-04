package ru.yoursweet667.uno.web.model;

import java.util.List;

public class GameResponse {
    /**
     * Url to join the game
     */
    private final String joinUrl;
    /**
     * Players
     */
    private final List<PlayerResponse> players;
    /**
     * Game Id
     */
    private final String gameId;

    public GameResponse(String gameId, List<PlayerResponse> players, String joinUrl) {
        this.joinUrl = joinUrl;
        this.players = players;
        this.gameId = gameId;
    }

    public String getJoinUrl() {
        return joinUrl;
    }

    public List<PlayerResponse> getPlayers() {
        return players;
    }

    public String getGameId() {
        return gameId;
    }
}