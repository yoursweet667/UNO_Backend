package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.GameState;

public class EndGameProcessor {

    private final Game game;

    public EndGameProcessor(Game game) {
        this.game = game;
    }


    public void process() {
        game.setGameState(GameState.GAME_ENDED);
    }
}
