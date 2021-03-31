package ru.yoursweet667.uno.service.model.event;

import ru.yoursweet667.uno.service.model.EventType;

public class StartGameEvent extends Event {

    public StartGameEvent(int eventId, EventType type) {
        super(eventId, type);
    }

}