package ru.yoursweet667.uno.service.event.validator;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.event.PlayCardEvent;

public class PlayCardValidator implements EventValidator<PlayCardEvent> {

    private final Game game;

    public PlayCardValidator(Game game) {
        this.game = game;
    }

    @Override
    public void validate(PlayCardEvent event) {
        String sourcePlayerId = event.getSourcePlayerId();
        String nextPlayerId = game.getNextPlayer()
                .orElseThrow(() -> new IllegalStateException("Next Player Isn't Found"))
                .getPlayerId();
        if (!sourcePlayerId.equals(nextPlayerId)) {
            throw new IllegalArgumentException("It Isn't " + sourcePlayerId + " Turn");
        }
    }
}