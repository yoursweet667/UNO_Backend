package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.event.Event;

import java.util.function.BiConsumer;

public interface EventProcessor<T extends Event> {

    void process(T event, Game game, BiConsumer<String, Event> resultEventConsumer);
}