package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.EventType;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.event.Event;
import ru.yoursweet667.uno.service.model.event.StartTurnEvent;
import ru.yoursweet667.uno.service.model.event.TurnCardOverEvent;

import java.util.function.BiConsumer;

public class TurnCardOverProcessor extends BaseEventProcessor<TurnCardOverEvent>{

    @Override
    void doProcess(TurnCardOverEvent event, Game game, BiConsumer<String, Event> resultEventConsumer) {
        game.getCardsInTheGame().add(event.getCard());

        StartTurnEvent startTurnEvent = new StartTurnEvent
                (event.getEventId() + 1, EventType.START_TURN, game.getNextPlayer().get());

        resultEventConsumer.accept(game.getGameId(), startTurnEvent);
    }
}
