package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.EventType;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.event.EndTurnEvent;
import ru.yoursweet667.uno.service.model.event.Event;
import ru.yoursweet667.uno.service.model.event.SkipTurnEvent;

import java.util.function.BiConsumer;

public class SkipTurnProcessor extends BaseEventProcessor<SkipTurnEvent> {

    @Override
    void doProcess(SkipTurnEvent event, Game game, BiConsumer<String, Event> resultEventConsumer) {

        EndTurnEvent endTurnEvent = new EndTurnEvent
                (event.getEventId() + 1, EventType.END_TURN, event.getPlayer());

        resultEventConsumer.accept(game.getGameId(), endTurnEvent);
    }
}