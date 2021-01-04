package ru.yoursweet667.uno.service.event;

import org.springframework.beans.factory.annotation.Autowired;
import ru.yoursweet667.uno.service.game.GameService;
import ru.yoursweet667.uno.web.model.Events;
import ru.yoursweet667.uno.web.model.Event;

public class EventServiceImpl implements EventService{

    @Autowired
    private final GameService gameService;

    public EventServiceImpl(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public Event createEvent(String gameId, Event event) {
        return null;
    }

    @Override
    public Events getEvents(String gameId, String playerId, Integer fromEventId) {
        return null;
    }


}