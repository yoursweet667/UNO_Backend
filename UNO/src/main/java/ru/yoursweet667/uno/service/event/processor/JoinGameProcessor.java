package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.event.JoinGameEvent;

public class JoinGameProcessor implements EventProcessor<JoinGameEvent>{

    private final Game game;

    public JoinGameProcessor(Game game) {
        this.game = game;
    }

    @Override
    public void process(JoinGameEvent event) {
        game.getPlayers().put(event.getPlayer().getPlayerId(), event.getPlayer());
        game.getEvents().add(event);
    }
}
