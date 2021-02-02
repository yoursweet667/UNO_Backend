package ru.yoursweet667.uno.service.model.event;

import ru.yoursweet667.uno.service.model.EventType;

public class EndGameEvent extends Event{
    public EndGameEvent(int eventId, EventType type) {
        super(eventId, type);
    }
}
