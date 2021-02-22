package ru.yoursweet667.uno.service.model.event;

import ru.yoursweet667.uno.service.model.EventType;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return eventId == event.eventId &&
                type == event.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, type);
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
