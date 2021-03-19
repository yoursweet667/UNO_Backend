package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.*;
import ru.yoursweet667.uno.service.model.event.Event;
import ru.yoursweet667.uno.service.model.event.StartGameEvent;
import ru.yoursweet667.uno.service.model.event.TakeCardsEvent;
import ru.yoursweet667.uno.service.model.event.TurnCardOverEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class StartGameProcessor extends BaseEventProcessor<StartGameEvent> {

    @Override
    void doProcess(StartGameEvent event, Game game, BiConsumer<String, Event> resultEventConsumer) {
        Player nextPlayer = game.getPlayers().values().iterator().next();
        game.setNextPlayer(nextPlayer);
        game.setGameState(GameState.START_TURN);

        for (Player player : game.getPlayers().values()) {

            List<Card> cards = new ArrayList<>();

            for (int i = 0; i < 7; i++) {

                cards.add(game.getDeck().get(i));
                game.getDeck().remove(i);
            }

            TakeCardsEvent takeCardsEvent = new TakeCardsEvent
                    (event.getEventId() + 1, EventType.TAKE_CARDS, cards, player);
            resultEventConsumer.accept(game.getGameId(), takeCardsEvent);
        }

        TurnCardOverEvent turnCardOverEvent = new TurnCardOverEvent
                (event.getEventId() + 2, EventType.TURN_CARD_OVER, game.getDeck().get(0));

        resultEventConsumer.accept(game.getGameId(), turnCardOverEvent);

    }
}