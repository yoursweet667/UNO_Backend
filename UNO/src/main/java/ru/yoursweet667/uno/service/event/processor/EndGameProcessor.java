package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.GameState;
import ru.yoursweet667.uno.service.model.event.EndGameEvent;
import ru.yoursweet667.uno.service.model.event.Event;

import java.util.function.BiConsumer;

public class EndGameProcessor extends BaseEventProcessor<EndGameEvent> {

    @Override
    void doProcess(EndGameEvent event, Game game, BiConsumer<String, Event> resultEventConsumer) {
        game.setGameState(GameState.GAME_ENDED);
    }
}
