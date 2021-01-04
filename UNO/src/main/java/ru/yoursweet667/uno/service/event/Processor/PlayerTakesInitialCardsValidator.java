package ru.yoursweet667.uno.service.event.Processor;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.PlayerTakesInitialCardsEvent;

public class PlayerTakesInitialCardsValidator implements EventValidator<PlayerTakesInitialCardsEvent>{

    private final Game game;

    public PlayerTakesInitialCardsValidator(Game game) {
        this.game = game;
    }

    @Override
    public void validate(PlayerTakesInitialCardsEvent event) {

    }
}
