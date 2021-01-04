package ru.yoursweet667.uno.service.game;

import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.Game;

public interface GameService {

    Game createGame();
    Player addPlayerToGame(String gameId, String playerName);
    void removePlayerFromGame(String gameId, String playerId);

}
