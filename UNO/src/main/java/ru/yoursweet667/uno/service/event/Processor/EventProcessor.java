package ru.yoursweet667.uno.service.event.Processor;

import ru.yoursweet667.uno.web.model.Event;

public interface EventProcessor<T extends Event> {

    void process(T event);
}