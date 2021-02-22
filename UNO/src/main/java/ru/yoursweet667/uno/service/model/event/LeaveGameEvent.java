package ru.yoursweet667.uno.service.model.event;

import ru.yoursweet667.uno.service.model.EventType;
import ru.yoursweet667.uno.service.model.Player;

import java.util.Objects;

public class LeaveGameEvent extends Event {
    /**
     * Player who left
     */
    private final Player player;

    public LeaveGameEvent(int eventId, EventType type, Player player) {
        super(eventId, type);
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LeaveGameEvent that = (LeaveGameEvent) o;
        return Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), player);
    }

    @Override
    public String toString() {
        return "LeaveGameEvent{" +
                "player=" + player +
                '}';
    }

    public Player getPlayer() {
        return player;
    }
}
