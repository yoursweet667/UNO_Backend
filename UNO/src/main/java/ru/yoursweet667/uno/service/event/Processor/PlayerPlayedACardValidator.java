package ru.yoursweet667.uno.service.event.Processor;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.PlayerPlayedACardEvent;

public class PlayerPlayedACardValidator implements EventValidator<PlayerPlayedACardEvent> {

    private final Game game;

    public PlayerPlayedACardValidator(Game game) {
        this.game = game;
    }

    @Override
    public void validate(PlayerPlayedACardEvent event) {
        validateSourcePlayerId(event.getSourcePlayerId());

    }
    public void validateSourcePlayerId(String sourcePlayerId){
        String nextPlayerId = game.getNextPlayer().orElseThrow(() -> new IllegalStateException("Next Player Isn't Found"))
                .getPlayerId();
        if (!sourcePlayerId.equals(nextPlayerId)) {
            throw new IllegalArgumentException("It Isn't "+ sourcePlayerId +" Turn");
        }
    }
}