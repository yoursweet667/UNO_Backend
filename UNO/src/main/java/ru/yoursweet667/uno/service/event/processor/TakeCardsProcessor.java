package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.GameState;
import ru.yoursweet667.uno.service.model.event.Event;
import ru.yoursweet667.uno.service.model.event.TakeCardsEvent;

import java.util.function.BiConsumer;

public class TakeCardsProcessor extends BaseEventProcessor<TakeCardsEvent> {

    @Override
    void doProcess(TakeCardsEvent event, Game game, BiConsumer<String, Event> resultEventConsumer) {
        event.getPlayer().getCards().addAll(event.getCards());

    }
}