package ru.yoursweet667.uno.service.event.validator;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.event.LeaveGameEvent;

public class LeaveGameValidator implements EventValidator<LeaveGameEvent>{

    private final Game game;

    public LeaveGameValidator(Game game) {
        this.game = game;
    }

    @Override
    public void validate(LeaveGameEvent event) {

        if (!game.getPlayers().containsKey(event.getPlayer().getPlayerId())) {
            throw new IllegalArgumentException("Player Already Left");
        }
    }
}
