package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.Game;

public class LeaveGameEvent implements EventProcessor<ru.yoursweet667.uno.service.model.event.LeaveGameEvent>{

    private final Game game;


    public LeaveGameEvent(Game game) {
        this.game = game;
    }

    @Override
    public void process(ru.yoursweet667.uno.service.model.event.LeaveGameEvent event) {
        game.getPlayers().remove(event.getPlayer().getPlayerId());
        game.getEvents().add(event);
    }
}
