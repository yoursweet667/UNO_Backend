package ru.yoursweet667.uno.service.event.Processor;

import ru.yoursweet667.uno.service.model.Event;

public interface EventValidator<T extends Event> {

    void validate(T event);
}