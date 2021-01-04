package ru.yoursweet667.uno.dataaccess.game;

import ru.yoursweet667.uno.service.model.Game;

public interface GameStorage {
    void createGame (Game game);
    void updateGame(Game game);
    void deleteGame(String gameId);
    Game getGame(String gameId);
}
