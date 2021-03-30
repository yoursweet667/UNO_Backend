package ru.yoursweet667.uno.service.event.processor;

import net.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.yoursweet667.uno.service.model.*;
import ru.yoursweet667.uno.service.model.event.EndGameEvent;
import ru.yoursweet667.uno.service.model.event.EndTurnEvent;
import ru.yoursweet667.uno.service.model.event.Event;
import ru.yoursweet667.uno.service.model.event.StartTurnEvent;

import java.util.*;
import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;


public class EndTurnProcessorTest {

    private static final EndTurnProcessor endTurnProcessor = new EndTurnProcessor();

    @Mock
    private BiConsumer<String, Event> biConsumer;

    @BeforeEach
    private void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void doProcess_playerHaveCardsToPlay_startTurn() {
        //Given
        Player player = new Player("playerId", null, null);
        List<Card> cards = new ArrayList<>();
        Card card = new Card(null, null, true);

        int i = 0;
        do {cards.add(card); i++; }
        while (i < 3);

        Player playerForEvent = new Player("playerIdFromEvent", null, cards);

        Map<String, Player> players = new HashMap<>();
        players.put(player.getPlayerId(), player);
        players.put(playerForEvent.getPlayerId(), player);

        Game game = new Game("gameId", players, GameState.START_TURN,
                null, null, null);
        EndTurnEvent event = new EndTurnEvent(123, null, playerForEvent);

        //When
        endTurnProcessor.doProcess(event, game, biConsumer);

        //Then
        assertThat(game.getGameState()).isEqualTo(GameState.END_TURN);
        Optional<Player> nextPlayer = game.getNextPlayer();
        assertThat(nextPlayer).isPresent();
        assertThat(nextPlayer.get().getPlayerId()).isEqualTo("playerId");
        game.getNextPlayer();
        StartTurnEvent startTurnEvent = new StartTurnEvent
                (event.getEventId() + 1, EventType.START_TURN, player);
        Mockito.verify(biConsumer).accept(game.getGameId(), startTurnEvent);
    }

    @Test
    void doProcess_playerHasNoMoreCardsToPlay_endGame() {
        //Given
        Player player = new Player("playerId", null, null);
        List<Card> cards = new ArrayList<>();
        Player playerForEvent = new Player("playerIdFromEvent", null, cards);
        Game game = new Game(null, Map.of(player.getPlayerId(), player), GameState.START_TURN,
                null, null, null);
        EndTurnEvent event = new EndTurnEvent(123, null, playerForEvent);

        //When
        endTurnProcessor.doProcess(event, game, biConsumer);

        //Then
        assertThat(game.getGameState()).isEqualTo(GameState.END_TURN);
        Optional<Player> nextPLayer = game.getNextPlayer();
        assertThat(nextPLayer).isPresent();
        assertThat(nextPLayer.get().getPlayerId()).isEqualTo("playerId");
        EndGameEvent endGameEvent = new EndGameEvent
                (event.getEventId() + 1, EventType.END_GAME);

        Mockito.verify(biConsumer).accept(game.getGameId(), endGameEvent);
    }

}
