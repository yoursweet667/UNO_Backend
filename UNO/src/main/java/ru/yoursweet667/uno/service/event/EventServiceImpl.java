package ru.yoursweet667.uno.service.event;

import org.springframework.beans.factory.annotation.Autowired;
import ru.yoursweet667.uno.service.game.GameService;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.event.Event;

import java.util.List;
import java.util.Optional;

public class EventServiceImpl implements EventService {

    @Autowired
    private GameService gameService;

    @Autowired
    private EventHandlerRegistry eventHandlerRegistry;

    @Override
    public void createEvent(String gameId, Event event) {
        Game game = gameService.getGame(gameId)
                .orElseThrow(() -> new IllegalArgumentException("Game not found"));

        eventHandlerRegistry.getValidator(event).validate(event, game);
        eventHandlerRegistry.getProcessor(event).process(event, game);
    }

    @Override
    public List<Event> getEvents(String gameId, String playerId, Integer fromEventId) {
        Game game = gameService.getGame(gameId)
                .orElseThrow(() -> new IllegalArgumentException("Game not found"));
        List<Event> events = game.getEvents();

        return events.subList(fromEventId, events.size());
    }
}