package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.Card;
import ru.yoursweet667.uno.service.model.EventType;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.event.EndTurnEvent;
import ru.yoursweet667.uno.service.model.event.Event;
import ru.yoursweet667.uno.service.model.event.PlayCardEvent;

import java.util.function.BiConsumer;

public class PlayCardProcessor extends BaseEventProcessor<PlayCardEvent> {

    @Override
    void doProcess(PlayCardEvent event, Game game, BiConsumer<String, Event> resultEventConsumer) {
        Card card = event.getCard();
        String sourcePlayerId = event.getSourcePlayerId();
        Player playerInTheGame = game.getPlayers().get(sourcePlayerId);
        playerInTheGame.getCards().remove(card);
        game.getCardsInTheGame().add(card);
    }
}