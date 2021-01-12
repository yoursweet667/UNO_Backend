package ru.yoursweet667.uno.service.event.validator;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.event.JoinGameEvent;

public class JoinGameValidator implements EventValidator<JoinGameEvent> {

    private final Game game;

    public JoinGameValidator(Game game) {
        this.game = game;
    }

    @Override
    public void validate(JoinGameEvent event) {

       if (game.getPlayers().containsKey(event.getPlayer().getPlayerId())) {
            throw new IllegalArgumentException("Player Already Joined");
        }
    }
}