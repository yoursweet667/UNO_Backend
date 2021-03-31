package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.event.Event;
import ru.yoursweet667.uno.service.model.event.LeaveGameEvent;

import java.util.function.BiConsumer;

public class LeaveGameProcessor extends BaseEventProcessor<LeaveGameEvent>{

    @Override
    void doProcess(LeaveGameEvent event, Game game, BiConsumer<String, Event> resultEventConsumer) {
        game.getPlayers().remove(event.getPlayer().getPlayerId());
    }
}
