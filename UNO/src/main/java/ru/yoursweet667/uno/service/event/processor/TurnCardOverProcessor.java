package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.event.TurnCardOverEvent;

public class TurnCardOverProcessor extends BaseEventProcessor<TurnCardOverEvent>{

    @Override
    void doProcess(TurnCardOverEvent event, Game game) {
        game.getCardsInTheGame().add(event.getCard());
    }
}
