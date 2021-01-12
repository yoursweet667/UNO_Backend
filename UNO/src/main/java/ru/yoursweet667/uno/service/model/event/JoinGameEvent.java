package ru.yoursweet667.uno.service.model.event;

import ru.yoursweet667.uno.service.model.EventType;
import ru.yoursweet667.uno.service.model.Player;

public class JoinGameEvent extends Event {

    /**
     * Player who joined
     */
    private final Player player;

    public JoinGameEvent(Integer eventId, EventType type, Player player) {
        super(eventId, type);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
