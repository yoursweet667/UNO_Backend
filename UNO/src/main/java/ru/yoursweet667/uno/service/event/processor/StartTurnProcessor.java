package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.event.Event;
import ru.yoursweet667.uno.service.model.event.StartTurnEvent;

import java.util.function.BiConsumer;

public class StartTurnProcessor extends BaseEventProcessor<StartTurnEvent> {

    @Override
    void doProcess(StartTurnEvent event, Game game, BiConsumer<String, Event> resultEventConsumer) {
        game.setNextPlayer(game.getPlayers().get(event.getPlayer().getPlayerId()));

    }
}