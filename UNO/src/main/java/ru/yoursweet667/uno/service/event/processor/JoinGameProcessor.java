package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.EventType;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.event.Event;
import ru.yoursweet667.uno.service.model.event.JoinGameEvent;
import ru.yoursweet667.uno.service.model.event.StartGameEvent;

import java.util.Map;
import java.util.function.BiConsumer;

public class JoinGameProcessor extends BaseEventProcessor<JoinGameEvent>{

    @Override
    void doProcess(JoinGameEvent event, Game game, BiConsumer<String, Event> resultEventConsumer) {
        game.getPlayers().put(event.getPlayer().getPlayerId(), event.getPlayer());

        if (game.getPlayers().size() == 2) {
            StartGameEvent startGameEvent = new StartGameEvent
                    (event.getEventId() + 1, EventType.START_GAME);
            resultEventConsumer.accept(game.getGameId(), startGameEvent);
        }
    }
}
