package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.event.Event;

public interface EventProcessor<T extends Event> {

    void process(T event);
}