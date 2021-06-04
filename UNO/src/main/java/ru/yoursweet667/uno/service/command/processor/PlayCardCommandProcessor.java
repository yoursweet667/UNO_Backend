package ru.yoursweet667.uno.service.command.processor;

import ru.yoursweet667.uno.service.command.model.PlayCardCommand;
import ru.yoursweet667.uno.service.model.Card;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.event.PlayCardEvent;

public class PlayCardCommandProcessor {

    void doProcess(PlayCardCommand command, Game game) {

        Player targetPlayer = null;
        Card card = command.getCard();
        String sourcePlayerId = command.getPlayerId();
        game.getCardsInTheGame().add(card);

        for (Player player : game.getPlayers().values()) {

            if (!player.getPlayerId().equals(sourcePlayerId)) {
                targetPlayer = player;
            }
        }

        PlayCardEvent event = new PlayCardEvent
                (game.getEvents().size() + 1, sourcePlayerId, targetPlayer.getPlayerId(), card);

        game.getEvents().add(event);
    }
}
