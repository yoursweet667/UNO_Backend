package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.event.SkipTurnEvent;

public class SkipTurnProcessor extends BaseEventProcessor<SkipTurnEvent> {

    @Override
    void doProcess(SkipTurnEvent event, Game game) {
        game.setNextPlayer(game.getPlayers().get(event.getPlayer().getPlayerId()));
    }
}