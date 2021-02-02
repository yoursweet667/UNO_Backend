package ru.yoursweet667.uno.service.event.validator;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.event.LeaveGameEvent;

public class LeaveGameValidator implements EventValidator<LeaveGameEvent> {

    @Override
    public void validate(LeaveGameEvent event, Game game) {
        if (!game.getPlayers().containsKey(event.getPlayer().getPlayerId())) {
            throw new IllegalArgumentException("Player Already Left");
        }
    }
}