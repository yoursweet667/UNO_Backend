package ru.yoursweet667.uno.service.event;


import ru.yoursweet667.uno.service.model.event.Event;

import java.util.List;

public interface EventService {

    void createEvent(String gameId, Event event);
    List<Event> getEvents(String gameId, String playerId, Integer fromEventId);

}