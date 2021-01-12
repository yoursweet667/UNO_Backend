package ru.yoursweet667.uno.service.event.validator;

import ru.yoursweet667.uno.service.model.event.Event;

public interface EventValidator<T extends Event> {

    void validate(T event);
}