package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.GameState;
import ru.yoursweet667.uno.service.model.event.TakeCardsEvent;

public class TakeCardsProcessor implements EventProcessor<TakeCardsEvent> {

    private final Game game;

    public TakeCardsProcessor(Game game) {
        this.game = game;
    }


    @Override
    public void process(TakeCardsEvent event) {
        if (game.getGameState() == GameState.INITIALISING) {
            event.getPlayer().getCards().addAll(event.getCards());
        } else {
            event.getPlayer().getCards().addAll(event.getCards());
        }
        game.getEvents().add(event);
    }
}