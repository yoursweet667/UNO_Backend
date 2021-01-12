package ru.yoursweet667.uno.service.model.event;

import ru.yoursweet667.uno.service.model.EventType;
import ru.yoursweet667.uno.service.model.Player;

public class SkipTurnEvent extends Event {

    /**
     * Player who starts turn
     */
    private final Player player;

    public SkipTurnEvent(Integer eventId, EventType type, Player player) {
        super(eventId, type);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}