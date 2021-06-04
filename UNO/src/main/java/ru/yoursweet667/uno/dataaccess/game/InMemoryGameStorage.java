package ru.yoursweet667.uno.dataaccess.game;

import org.springframework.stereotype.Component;
import ru.yoursweet667.uno.dataaccess.game.exception.InvalidGameStorageRequestException;
import ru.yoursweet667.uno.service.model.Game;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class InMemoryGameStorage implements ru.yoursweet667.uno.dataaccess.game.GameStorage {

    private final Map<String, Game> gameMap = new HashMap<>();

    @Override
    public void createGame(Game game) {
        if (gameMap.containsKey(game.getGameId())) {
            throw new InvalidGameStorageRequestException("Game with id: " + game.getGameId() + " already exist");
        } else
        gameMap.put(game.getGameId(), game);
    }

    @Override
    public void updateGame(Game game) {
       String gameId = game.getGameId();
       if (!gameMap.containsKey(gameId))  {
           throw new InvalidGameStorageRequestException("Game with id: " + gameId + " doesn't exist");
       } else
       gameMap.put(gameId, game);
    }

    @Override
    public void deleteGame(String gameId) {
        if (!gameMap.containsKey(gameId))  {
            throw new InvalidGameStorageRequestException("Game with id: " + gameId + " doesn't exist");
        } else
            gameMap.remove(gameId);
    }

    @Override
    public Optional<Game> getGame(String gameId) {
            return Optional.ofNullable(gameMap.get(gameId));
    }
}
