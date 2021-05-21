package ru.yoursweet667.uno.service.command.processor;

import ru.yoursweet667.uno.service.command.model.TakeCardCommand;
import ru.yoursweet667.uno.service.model.Card;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.event.TakeCardsEvent;

import java.util.List;

public class TakeCardCommandProcessor {

    void doProcess(TakeCardCommand command, Game game) {

        Card card = game.getDeck().get(0);
        game.getDeck().remove(card);
        Player player = game.getPlayers().get(command.getPlayerId());
        player.getCards().add(card);

        TakeCardsEvent event = new TakeCardsEvent
                (game.getEvents().size() + 1, List.of(card), player);

        game.getEvents().add(event);
    }
}
