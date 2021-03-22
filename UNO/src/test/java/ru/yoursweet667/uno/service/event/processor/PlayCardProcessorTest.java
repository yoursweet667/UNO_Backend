package ru.yoursweet667.uno.service.event.processor;

import org.junit.jupiter.api.Test;
import ru.yoursweet667.uno.service.model.*;
import ru.yoursweet667.uno.service.model.event.PlayCardEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class PlayCardProcessorTest {

    private final PlayCardProcessor playCardProcessor = new PlayCardProcessor();

    @Test
    void doProcess_addCardToGameAndRemoveItFromPlayer() {
        //Given
        List<Card> cards = new ArrayList<>();
        Card card = new Card(CardType.FIVE, CardColour.BLUE);
        cards.add(card);
        List<Card> cardsInGame = new ArrayList<>();
        Player player = new Player("sourcePlayerId", null, cards);
        PlayCardEvent event = new PlayCardEvent(123, "sourcePlayerId", null, card);
        Game game = new Game(null, Map.of(player.getPlayerId(), player),
                null, null, cardsInGame, null);

        //When
        playCardProcessor.doProcess(event, game, null);

        //Then
        assertThat(game.getCardsInTheGame()).endsWith(card);
        assertThat(game.getPlayers().get(player.getPlayerId()).getCards()).doesNotContain(card);
    }
}