package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.event.SkipTurnEvent;

public class SkipTurnProcessor implements EventProcessor<SkipTurnEvent> {

    private final Game game;

    public SkipTurnProcessor(Game game) {
        this.game = game;
    }

    @Override
    public void process(SkipTurnEvent event) {
        game.setNextPlayer(game.getPlayers().get(event.getPlayer().getPlayerId()));
        game.getEvents().add(event);
    }
}