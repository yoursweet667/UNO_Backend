package ru.yoursweet667.uno.dataaccess.game;

import org.springframework.stereotype.Component;
import ru.yoursweet667.uno.service.model.Game;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class InMemoryGameStorage implements ru.yoursweet667.uno.dataaccess.game.GameStorage {

    private final Map<String, Game> gameMap = new HashMap<>();

    @Override
    public void createGame(Game game) {
        //todo: Drop the error if game exist
        gameMap.put(game.getGameId(), game);
    }

    @Override
    public void updateGame(Game game) {
       String gameId = game.getGameId();
       //todo: Drop the error if game doesn't exist
       gameMap.put(gameId, game);
    }

    @Override
    public void deleteGame(String gameId) {
        //todo: Drop the error if game doesn't exist
        gameMap.remove(gameId);
    }

    @Override
    public Optional<Game> getGame(String gameId) {
        //todo: Drop the error if game exist
        return Optional.ofNullable(gameMap.get(gameId));
    }
}
