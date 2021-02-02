package ru.yoursweet667.uno.service.event;

import org.springframework.beans.factory.annotation.Autowired;
import ru.yoursweet667.uno.service.game.GameService;
import ru.yoursweet667.uno.service.model.event.Event;

import java.util.List;

public class EventServiceImpl implements EventService {

    @Autowired
    private GameService gameService;

    @Autowired
    private EventHandlerRegistry eventHandlerRegistry;

    @Override
    public void createEvent(String gameId, Event event) {
        eventHandlerRegistry.getValidator(event).validate(event, gameService.getGame(gameId));
        eventHandlerRegistry.getProcessor(event).process(event, gameService.getGame(gameId));
    }

    @Override
    public List<Event> getEvents(String gameId, String playerId, Integer fromEventId) {
        List<Event> events = gameService.getGame(gameId).getEvents();
        List<Event> neededEvents = events.subList(fromEventId, events.size());
        //todo: Error if the game is not found by provided gameId
        return null;
    }
}