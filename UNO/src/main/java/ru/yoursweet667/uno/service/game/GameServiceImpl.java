package ru.yoursweet667.uno.service.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yoursweet667.uno.dataaccess.game.GameStorage;
import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.Game;
import java.util.*;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameStorage storage;

    @Override
    public Game createGame() {
        String gameId = UUID.randomUUID().toString();
        Map<String, Player> players = new HashMap<>();
        Game game = new Game(gameId, players);
        storage.createGame(game);
        //todo: Drop the error if game exist
        return game;
    }

    @Override
    public Player addPlayerToGame(String gameId, String playerName) {
        String playerId = UUID.randomUUID().toString();
        Player player = new Player(playerId, playerName);
        storage.getGame(gameId).getPlayers().put(gameId, player);
        //todo: Drop the error if game doesn't exist
        return player;
    }

    @Override
    public void removePlayerFromGame(String gameId, String playerId) {
        storage.getGame(gameId).getPlayers().remove(playerId);
        //todo: Drop the error if game or player doesn't exist
    }

    public Game getGame(String gameId){
        return storage.getGame(gameId);
    }
}