package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.event.StartTurnEvent;

public class StartTurnProcessor extends BaseEventProcessor<StartTurnEvent>{

    @Override
    void doProcess(StartTurnEvent event, Game game) {
        game.setNextPlayer(game.getPlayers().get(event.getPlayer().getPlayerId()));
    }
}
