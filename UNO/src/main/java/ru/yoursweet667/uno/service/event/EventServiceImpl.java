package ru.yoursweet667.uno.service.event;

import org.springframework.beans.factory.annotation.Autowired;
import ru.yoursweet667.uno.service.game.GameService;
import ru.yoursweet667.uno.service.model.Card;
import ru.yoursweet667.uno.service.model.EventType;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.event.Event;
import ru.yoursweet667.uno.service.model.event.PlayCardEvent;
import ru.yoursweet667.uno.service.model.event.TakeCardsEvent;
import ru.yoursweet667.uno.service.model.event.TurnCardOverEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        eventHandlerRegistry.getProcessor(event).process(event, game, this::createEvent);
    }

    @Override
    //todo: use playerId
    public List<Event> getEvents(String gameId, String playerId, Integer fromEventId) {
        Game game = gameService.getGame(gameId)
                .orElseThrow(() -> new IllegalArgumentException("Game not found"));
        List<Event> events = game.getEvents();

        return events.subList(fromEventId, events.size()).stream()
                .map(event -> hideSensitiveDataFromPlayer(event, playerId))
                .collect(Collectors.toList());
    }

    private Event hideSensitiveDataFromPlayer(Event event, String playerId) {

       if (event.getType().equals(EventType.TAKE_CARDS)) {
            TakeCardsEvent takeCardsEvent = (TakeCardsEvent) event;

            if (!playerId.equals(takeCardsEvent.getPlayer().getPlayerId())) {
                List<Card> newCards = takeCardsEvent.getCards().stream()
                        .map(this::hideCardsDetails)
                        .collect(Collectors.toList());

                TakeCardsEvent changedTakeCardsEvent = new TakeCardsEvent
                        (takeCardsEvent.getEventId(), takeCardsEvent.getType(), newCards, takeCardsEvent.getPlayer());

                return changedTakeCardsEvent;
           }
            else { return takeCardsEvent; }
        }
       else { return event; }
    }

    private Card hideCardsDetails(Card card) {

        return new Card(card.getType(), card.getColour(), false);
    }
}