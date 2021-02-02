package ru.yoursweet667.uno.service.event.validator;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.event.JoinGameEvent;

public class JoinGameValidator implements EventValidator<JoinGameEvent> {

    @Override
    public void validate(JoinGameEvent event, Game game) {
        if (game.getPlayers().containsKey(event.getPlayer().getPlayerId())) {
            throw new IllegalArgumentException("Player Already Joined");

        }
    }
}