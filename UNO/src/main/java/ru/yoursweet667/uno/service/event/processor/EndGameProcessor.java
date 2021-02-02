package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.GameState;
import ru.yoursweet667.uno.service.model.event.EndGameEvent;

public class EndGameProcessor extends BaseEventProcessor<EndGameEvent> {

    @Override
    void doProcess(EndGameEvent event, Game game) {
        game.setGameState(GameState.GAME_ENDED);
    }
}
