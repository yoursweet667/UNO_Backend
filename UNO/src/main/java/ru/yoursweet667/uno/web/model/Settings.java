package ru.yoursweet667.uno.web.model;

public class Settings {
    /**
     * How often client should poll server for new events
     */
    private final int eventPollIntervalMillis;

    public Settings(int eventPollIntervalMillis) {
        this.eventPollIntervalMillis = eventPollIntervalMillis;
    }

    public int getEventPollIntervalMillis() {
        return eventPollIntervalMillis;
    }
}
