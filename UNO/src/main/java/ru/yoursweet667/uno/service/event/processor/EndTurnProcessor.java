package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.EventType;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.GameState;
import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.event.*;

import java.util.function.BiConsumer;

public class EndTurnProcessor extends BaseEventProcessor<EndTurnEvent> {

    @Override
    void doProcess(EndTurnEvent event, Game game, BiConsumer<String, Event> resultEventConsumer) {
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

        if (event.getPlayer().getCards().size() == 0) {
            EndGameEvent endGameEvent = new EndGameEvent
                    (event.getEventId() + 1, EventType.END_GAME);
            resultEventConsumer.accept(game.getGameId(), endGameEvent);
        }
        else {
            StartTurnEvent startTurnEvent = new StartTurnEvent
                    (event.getEventId() + 1, EventType.START_TURN, nextPlayer);
            resultEventConsumer.accept(game.getGameId(), startTurnEvent);
        }
    }
}