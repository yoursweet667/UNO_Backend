package ru.yoursweet667.uno.service.model.event;

import ru.yoursweet667.uno.service.model.EventType;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.GameState;
import ru.yoursweet667.uno.service.model.Player;

import java.util.Map;

public class StartGameEvent extends Event {

    /**
     * Players In The Game
     */
    private final Map<String, Player> players;

    public StartGameEvent(Integer eventId, EventType type, Map<String, Player> players) {
        super(eventId, type);
        this.players = players;
    }

    @Override
    public String toString() {
        return "StartGameEvent{" +
                "players=" + players +
                '}';
    }

    public Map<String, Player> getPlayers() {
        return players;
    }
}
