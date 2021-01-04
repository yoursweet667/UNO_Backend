package ru.yoursweet667.uno.service.model;

public abstract class Event {
    /**
     * Id of event
     */
    private final Integer eventId;
    /**
     * Type of event
     */
    private final ru.yoursweet667.uno.service.model.EventType type;

    public Event(Integer eventId, ru.yoursweet667.uno.service.model.EventType type) {
        this.eventId = eventId;
        this.type = type;
    }

    public Integer getEventId() {
        return eventId;
    }

    public EventType getType() {
        return type;
    }

}
