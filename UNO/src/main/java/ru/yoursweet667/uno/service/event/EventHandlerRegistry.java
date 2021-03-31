package ru.yoursweet667.uno.service.event;

import ru.yoursweet667.uno.service.event.processor.*;
import ru.yoursweet667.uno.service.event.validator.*;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.event.*;

import java.util.Map;

 public class EventHandlerRegistry {

    private final Map<Class<? extends Event>, EventProcessor<?>> eventTypeToProcessorMap;
    private final Map<Class<? extends Event>, EventValidator<?>> eventTypeToValidatorMap;

    EventHandlerRegistry() {
        eventTypeToProcessorMap = Map.of(
                JoinGameEvent.class, new JoinGameProcessor(),
                LeaveGameEvent.class, new LeaveGameProcessor(),
                EndGameEvent.class, new EndGameProcessor(),
                EndTurnEvent.class, new EndTurnProcessor(),
                PlayCardEvent.class, new PlayCardProcessor(),
                SkipTurnEvent.class, new SkipTurnProcessor(),
                StartGameEvent.class, new StartGameProcessor(),
                TakeCardsEvent.class, new TakeCardsProcessor(),
                TurnCardOverEvent.class, new TurnCardOverProcessor(),
                StartTurnEvent.class, new StartTurnProcessor()
        );

        eventTypeToValidatorMap = Map.of(
                JoinGameEvent.class, new JoinGameValidator(),
                LeaveGameEvent.class, new LeaveGameValidator(),
                PlayCardEvent.class, new PlayCardValidator(),
                TakeCardsEvent.class, new TakeCardsValidator(),
                TurnCardOverEvent.class, new TurnCardOverValidator()
        );
    }

    <T extends Event> EventProcessor<T> getProcessor(T event) {
        Class<? extends Event> typeOfEvent = event.getClass();
        return (EventProcessor<T>) eventTypeToProcessorMap.get(typeOfEvent);
    }

    <T extends Event> EventValidator<T> getValidator(T event) {
        Class<? extends Event> typeOfEvent = event.getClass();
        return (EventValidator<T>) eventTypeToValidatorMap.get(typeOfEvent);

    }
}