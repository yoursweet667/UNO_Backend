package ru.yoursweet667.uno.service.event.Processor;

import ru.yoursweet667.uno.service.model.FirstCardTurnedUpEvent;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.GameState;

public class FirstCardTurnedUpValidator implements EventValidator<FirstCardTurnedUpEvent> {

    private final Game game;

    public FirstCardTurnedUpValidator(Game game) {
        this.game = game;
    }

    @Override
    public void validate(FirstCardTurnedUpEvent event) {
        validateFirstCardTurnedUp((event.getGameState()));
    }

    public void validateFirstCardTurnedUp(GameState gameState) {
        GameState currentGameState = game.getGameState();

        //todo: Drop the error if game state doesn't equals current one

    }
}