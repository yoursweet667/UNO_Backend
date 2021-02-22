package ru.yoursweet667.uno.service.event.validator;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.event.PlayCardEvent;

import java.util.Optional;

public class PlayCardValidator implements EventValidator<PlayCardEvent> {


    @Override
    public void validate(PlayCardEvent event, Game game) {
        String sourcePlayerId = event.getSourcePlayerId();
        Player player = game.getNextPlayer()
                .orElseThrow(() -> new IllegalStateException("Next Player Isn't Found"));

        if (!sourcePlayerId.equals(player.getPlayerId())) {
            throw new IllegalArgumentException("It Isn't " + sourcePlayerId + " Turn");
        }

        if (!player.getCards().contains(event.getCard())) {
            throw new IllegalArgumentException(player + "don't have expected card");
        }
    }
}