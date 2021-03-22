package ru.yoursweet667.uno.service.event.processor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.yoursweet667.uno.service.game.CardFactory;
import ru.yoursweet667.uno.service.model.*;
import ru.yoursweet667.uno.service.model.event.Event;
import ru.yoursweet667.uno.service.model.event.StartGameEvent;
import ru.yoursweet667.uno.service.model.event.TakeCardsEvent;
import ru.yoursweet667.uno.service.model.event.TurnCardOverEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;

public class StartGameProcessorTest {

    private final StartGameProcessor startGameProcessor = new StartGameProcessor();

    @Mock
    private BiConsumer<String, Event> biConsumer;

    @BeforeEach
    private void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void doProcess_setNextPlayerAndSetGameState() {
        //Given
        List<Card> cardsFirstPlayer = new ArrayList<>();
        List<Card> cardsSecondPlayer = new ArrayList<>();

        Player firstPlayer = new Player("playerId", null, cardsFirstPlayer);
        Player secondPlayer = new Player("playerId", null, cardsSecondPlayer);

        Map<String, Player> players = new HashMap<>();
        players.put(firstPlayer.getPlayerId(), firstPlayer);
        players.put(secondPlayer.getPlayerId(), secondPlayer);

        List<Card> deck = CardFactory.createDeck();
        Game game = new Game("gameId", players, null,
                deck, null, null);
        StartGameEvent event = new StartGameEvent(1, EventType.START_GAME);
        List<Card> deckClone = new ArrayList<>(game.getDeck());

        //When
        startGameProcessor.doProcess(event, game, biConsumer);

        //Then
        assertThat(game.getGameState()).isEqualTo(GameState.START_TURN);
        assertThat(game.getNextPlayer()).isPresent();


        for (Player player : game.getPlayers().values()) {

            List<Card> cards = new ArrayList<>();

            for (int i = 0; i < 7; i++) {

                cards.add(deckClone.get(i));
                deckClone.remove(i);
            }

            TakeCardsEvent takeCardsEvent = new TakeCardsEvent
                    (event.getEventId()+ 1, EventType.TAKE_CARDS, cards, player);

            Mockito.verify(biConsumer).accept(game.getGameId(), takeCardsEvent );
        }
        TurnCardOverEvent turnCardOverEvent = new TurnCardOverEvent
                (event.getEventId() + 2, EventType.TURN_CARD_OVER, game.getDeck().get(0));
        Mockito.verify(biConsumer).accept(game.getGameId(), turnCardOverEvent );
    }
}
