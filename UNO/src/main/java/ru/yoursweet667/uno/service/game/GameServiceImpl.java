package ru.yoursweet667.uno.service.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yoursweet667.uno.dataaccess.game.GameStorage;
import ru.yoursweet667.uno.service.exception.GameNotFoundException;
import ru.yoursweet667.uno.service.exception.PlayerAlreadyExistsException;
import ru.yoursweet667.uno.service.exception.PlayerNotFoundException;
import ru.yoursweet667.uno.service.model.*;
import ru.yoursweet667.uno.service.model.event.Event;

import java.util.*;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameStorage storage;

    @Override
    public Game createGame() {
        String gameId = UUID.randomUUID().toString();
        Map<String, Player> players = new HashMap<>();
        GameState gameState = GameState.GAME_CREATED;
        List<Card> deck = CardFactory.createDeck();
        List<Card> cardsInTheGame = new ArrayList<>();
        List<Event> events = new LinkedList<>();
        Game game = new Game(gameId, players, gameState, deck, cardsInTheGame, events);
        storage.createGame(game);
        return game;
    }

    @Override
    public Player addPlayerToGame(String gameId, String playerName) {
        List<Card> playerCards = new ArrayList<>();
        Player player = new Player(UUID.randomUUID().toString(), playerName, playerCards);

        if (getGameFromOptional(gameId).getPlayers().containsValue(player)) {
            throw new PlayerAlreadyExistsException("Player: " + playerName + "already exist");
        } else
        getGameFromOptional(gameId).getPlayers().put(player.getPlayerId(), player);
        return player;
    }

    @Override
    public void removePlayerFromGame(String gameId, String playerId) {
        Game game = getGameFromOptional(gameId);
        if (!game.getPlayers().containsKey(playerId)) {
            throw new PlayerNotFoundException("Player: " + playerId + "not found");
        } else
        getGameFromOptional(gameId).getPlayers().remove(playerId);
    }

    @Override
    public Optional<Game> getGame(String gameId) {
        return storage.getGame(gameId);
    }

    private Game getGameFromOptional(String gameId) {
        return storage.getGame(gameId)
                .orElseThrow(() -> new GameNotFoundException("Game with id: " + gameId + "not found"));
    }
}