package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.GameState;
import ru.yoursweet667.uno.service.model.event.StartGameEvent;
import ru.yoursweet667.uno.service.model.Player;

public class StartGameProcessor implements EventProcessor<StartGameEvent>{

    private final Game game;

    public StartGameProcessor(Game game) {
        this.game = game;
    }

    @Override
    public void process(StartGameEvent event) {

        Player nextPlayer = game.getPlayers().values().iterator().next();
        game.setNextPlayer(nextPlayer);
        game.setGameState(GameState.START_TURN);
        game.getEvents().add(event);

    }
}
