package ru.yoursweet667.uno.web.model;

import java.util.List;

public class Events {
    /**
     * List of events
     */
    private final List<Event> events;

    public Events(List<Event> events) {
        this.events = events;
    }

    public List<Event> getEvents() {
        return events;
    }
}
