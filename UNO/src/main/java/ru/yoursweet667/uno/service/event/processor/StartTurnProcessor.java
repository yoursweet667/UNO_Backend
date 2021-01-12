package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.event.StartTurnEvent;

public class StartTurnProcessor implements EventProcessor<StartTurnEvent>{

    private final Game game;


    public StartTurnProcessor(Game game) {
        this.game = game;
    }

    @Override
    public void process(StartTurnEvent event) {
        game.setNextPlayer(game.getPlayers().get(event.getPlayer().getPlayerId()));
        game.getEvents().add(event);
    }
}
