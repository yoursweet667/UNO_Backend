package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.event.TurnCardOverEvent;

public class TurnedCardOverProcessor implements EventProcessor<TurnCardOverEvent>{

    private final Game game;


    public TurnedCardOverProcessor(Game game) {
        this.game = game;
    }

    @Override
    public void process(TurnCardOverEvent event) {
        game.getCardsInTheGame().add(event.getCard());
        game.getEvents().add(event);
    }
}
