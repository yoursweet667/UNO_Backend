package ru.yoursweet667.uno.service.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yoursweet667.uno.dataaccess.game.GameStorage;
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
        List<Card> deck = new ArrayList<>();
        List<Card> cardsInTheGame = new ArrayList<>();
        List<Event> events = new LinkedList<>();
        Game game = new Game(gameId, players, gameState, deck, cardsInTheGame, events);
        storage.createGame(game);
        //todo: Drop the error if game exist
        return game;
    }

    @Override
    public Player addPlayerToGame(String gameId, String playerName) {
        List<Card> playerCards = new ArrayList<>();
        Player player = new Player(UUID.randomUUID().toString(), playerName, playerCards);
        storage.getGame(gameId).getPlayers().put(player.getPlayerId(), player);
        //todo: Drop the error if game doesn't exist
        return player;
    }

    @Override
    public void removePlayerFromGame(String gameId, String playerId) {
        storage.getGame(gameId).getPlayers().remove(playerId);
        //todo: Drop the error if game or player doesn't exist
    }

    public Game getGame(String gameId) {
        return storage.getGame(gameId);
    }

    public List<Card> createCards(int numberOfCards, CardType cardType) {
        List<Card> cards = new ArrayList<>();

        if (cardType.equals(CardType.CHANGE_COLOUR)) {
            for (int i = 1; i <= numberOfCards; i++) {
                Card cardChangeColour = new Card(cardType, CardColour.BLACK);
                cards.add(cardChangeColour);
            }
        } else if (cardType.equals(CardType.PLUS_4)) {
            for (int i = 1; i <= numberOfCards; i++) {
                Card cardPlus4 = new Card(cardType, CardColour.BLACK);
                cards.add(cardPlus4);
            }
        }
        for (int i = 1; i <= numberOfCards; i++) {
            Card redCard = new Card(cardType, CardColour.RED);
            Card blueCard = new Card(cardType, CardColour.BLUE);
            Card greenCard = new Card(cardType, CardColour.GREEN);
            Card yellowCard = new Card(cardType, CardColour.YELLOW);
            cards.add(redCard);
            cards.add(blueCard);
            cards.add(greenCard);
            cards.add(yellowCard);
        }
        return cards;
    }
}