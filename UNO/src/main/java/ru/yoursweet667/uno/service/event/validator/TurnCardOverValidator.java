package ru.yoursweet667.uno.service.event.validator;

import ru.yoursweet667.uno.service.model.event.TurnCardOverEvent;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.GameState;

public class TurnCardOverValidator implements EventValidator<TurnCardOverEvent> {

    @Override
    public void validate(TurnCardOverEvent event, Game game) {
        GameState gameState = game.getGameState();
        GameState requiredGameState = GameState.INITIALISING;
        if (gameState != requiredGameState) {
            throw new IllegalArgumentException("Incorrect Game State");
        }
    }
}