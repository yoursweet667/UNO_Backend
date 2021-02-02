package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.event.JoinGameEvent;

public class JoinGameProcessor extends BaseEventProcessor<JoinGameEvent>{

    @Override
    void doProcess(JoinGameEvent event, Game game) {
        game.getPlayers().put(event.getPlayer().getPlayerId(), event.getPlayer());

    }
}
