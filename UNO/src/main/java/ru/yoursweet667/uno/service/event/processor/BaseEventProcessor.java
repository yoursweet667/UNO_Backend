package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.event.Event;

abstract class BaseEventProcessor<T extends Event> implements EventProcessor<T> {

    @Override
    public void process(T event, Game game) {
        doProcess(event, game);
        game.getEvents().add(event);
    }

    abstract void doProcess(T event, Game game);

}
