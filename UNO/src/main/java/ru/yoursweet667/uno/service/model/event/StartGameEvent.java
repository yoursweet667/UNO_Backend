package ru.yoursweet667.uno.service.model.event;

import ru.yoursweet667.uno.service.model.EventType;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.GameState;
import ru.yoursweet667.uno.service.model.Player;

import java.util.Map;
import java.util.Objects;

public class StartGameEvent extends Event {

    /**
     * Players In The Game
     */
    private final Map<String, Player> players;

    public StartGameEvent(int eventId, EventType type, Map<String, Player> players) {
        super(eventId, type);
        this.players = players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StartGameEvent that = (StartGameEvent) o;
        return Objects.equals(players, that.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), players);
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
