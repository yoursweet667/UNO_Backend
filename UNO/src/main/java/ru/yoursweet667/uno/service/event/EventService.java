package ru.yoursweet667.uno.service.event;

import ru.yoursweet667.uno.web.model.Event;
import ru.yoursweet667.uno.web.model.Events;

public interface EventService {

    Event createEvent(String gameId, Event event);
    Events getEvents(String gameId, String playerId, Integer fromEventId);

}