package ru.yoursweet667.uno.service.model.event;

import ru.yoursweet667.uno.service.model.EventType;
import ru.yoursweet667.uno.service.model.Player;

public class EndTurnEvent extends Event {

    /**
     * Player who starts turn
     */
    private final Player player;

    public EndTurnEvent(Integer eventId, EventType type, Player player) {
        super(eventId, type);
        this.player = player;
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
