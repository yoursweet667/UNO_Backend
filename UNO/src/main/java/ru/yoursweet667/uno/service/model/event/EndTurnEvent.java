package ru.yoursweet667.uno.service.model.event;

import ru.yoursweet667.uno.service.model.EventType;
import ru.yoursweet667.uno.service.model.Player;

import java.util.Objects;

public class EndTurnEvent extends Event {

    /**
     * Player who starts turn
     */
    private final Player player;

    public EndTurnEvent(int eventId, EventType type, Player player) {
        super(eventId, type);
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EndTurnEvent that = (EndTurnEvent) o;
        return Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player);
    }

    @Override
    public String toString() {
        return "EndTurnEvent{" +
                "player=" + player +
                '}';
    }

    public Player getPlayer() {
        return player;
    }
}
