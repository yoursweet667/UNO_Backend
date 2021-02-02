package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.GameState;
import ru.yoursweet667.uno.service.model.event.TakeCardsEvent;

public class TakeCardsProcessor extends BaseEventProcessor<TakeCardsEvent> {

    @Override
    void doProcess(TakeCardsEvent event, Game game) {
        if (game.getGameState() == GameState.INITIALISING) {
            event.getPlayer().getCards().addAll(event.getCards());
        } else {
            event.getPlayer().getCards().addAll(event.getCards());
        }
    }
}