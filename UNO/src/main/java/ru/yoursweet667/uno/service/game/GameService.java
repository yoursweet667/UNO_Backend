package ru.yoursweet667.uno.service.game;

import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.Game;

import java.util.Optional;

public interface GameService {

    Game createGame();
    Player addPlayerToGame(String gameId, String playerId);
    void removePlayerFromGame(String gameId, String playerId);
    Optional<Game> getGame(String gameId);

}
