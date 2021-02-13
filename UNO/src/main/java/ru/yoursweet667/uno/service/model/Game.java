package ru.yoursweet667.uno.service.model;

import ru.yoursweet667.uno.service.model.event.Event;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Game {
    /**
     * Game Id
     */
    private final String gameId;
    /**
     * Players
     */
    private final Map<String, Player> players;
    /**
     * Game State
     */
    private GameState gameState;
    /**
     * Next Player
     */
    private Player nextPlayer;
    /**
     *
     */
    private final List<Card> deck;
    /**
     *
     */
    private final List<Card> cardsInTheGame;
    /**
     *
     */
    private final List<Event> events;



    public Game(String gameId, Map<String, Player> players, GameState gameState,
                List<Card> deck, List<Card> cardsInTheGame, List<Event> events) {
        this.gameId = gameId;
        this.players = players;
        this.gameState = gameState;
        this.deck = deck;
        this.cardsInTheGame = cardsInTheGame;
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(gameId, game.gameId) &&
                Objects.equals(players, game.players) &&
                gameState == game.gameState &&
                Objects.equals(nextPlayer, game.nextPlayer) &&
                Objects.equals(deck, game.deck) &&
                Objects.equals(cardsInTheGame, game.cardsInTheGame) &&
                Objects.equals(events, game.events);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, players, gameState, nextPlayer, deck, cardsInTheGame, events);
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId='" + gameId + '\'' +
                ", players=" + players +
                ", gameState=" + gameState +
                ", nextPlayer=" + nextPlayer +
                ", deck=" + deck +
                ", cardsInTheGame=" + cardsInTheGame +
                ", events=" + events +
                '}';
    }

    public String getGameId() {
        return gameId;
    }

    public Map<String, Player> getPlayers() {
        return players;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Optional<Player> getNextPlayer() {
        return Optional.ofNullable(nextPlayer);
    }

    public List<Card> getDeck() {
        return deck;
    }

    public List<Card> getCardsInTheGame() {
        return cardsInTheGame;
    }

    public List<Event> getEvents() {
        return events;
    }
    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Optional<Card> getLastCardInTheGame() {
        int gameCardsSize = cardsInTheGame.size();
        if (gameCardsSize < 1){
            return Optional.empty();
        }
        return Optional.ofNullable(cardsInTheGame.get(gameCardsSize - 1));
    }
}