package ru.yoursweet667.uno.service.model.event;

import ru.yoursweet667.uno.service.model.EventType;

public abstract class Event {
    /**
     * Id of event
     */
    private final int eventId;
    /**
     * Type of event
     */
    private final EventType type;

    public Event(int eventId, EventType type) {
        this.eventId = eventId;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", type=" + type +
                '}';
    }

    public Integer getEventId() {
        return eventId;
    }

    public EventType getType() {
        return type;
    }

}
