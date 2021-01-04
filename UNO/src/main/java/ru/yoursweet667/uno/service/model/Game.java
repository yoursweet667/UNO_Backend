package ru.yoursweet667.uno.service.model;

import java.util.Map;
import java.util.Optional;

public class Game {
    /**
     * Game Id
     */
    private final String gameId;
    /**
     * Players
     */
    private final Map<String, Player> players;
    /**
     * Game State
     */
    private GameState gameState;
    /**
     * Next Player
     */
    private Player nextPlayer;



    public Game(String gameId, Map<String, Player> players) {
        this.gameId = gameId;
        this.players = players;
    }

    public String getGameId() {
        return gameId;
    }

    public Map<String, Player> getPlayers() {
        return players;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Optional<Player> getNextPlayer() {
        return Optional.ofNullable(nextPlayer);
    }
}