package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.event.LeaveGameEvent;

public class LeaveGameProcessor extends BaseEventProcessor<LeaveGameEvent>{

    @Override
    void doProcess(LeaveGameEvent event, Game game) {
        game.getPlayers().remove(event.getPlayer().getPlayerId());
    }
}
