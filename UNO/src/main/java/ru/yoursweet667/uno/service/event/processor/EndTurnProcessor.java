package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.GameState;
import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.event.EndTurnEvent;

public class EndTurnProcessor implements EventProcessor<EndTurnEvent> {

    private final Game game;

    public EndTurnProcessor(Game game) {
        this.game = game;
    }

    @Override
    public void process(EndTurnEvent event) {

        Player nextPlayer = null;
        var players = game.getPlayers().values();
        for (var player : players) {
            if (!event.getPlayer().equals(player)) {
                nextPlayer = player;
                break;
            }
        }
        game.setNextPlayer(nextPlayer);
        game.setGameState(GameState.END_TURN);
        game.getEvents().add(event);
    }
}