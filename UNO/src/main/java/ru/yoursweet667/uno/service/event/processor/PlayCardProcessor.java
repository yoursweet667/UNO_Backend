package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.Card;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.event.PlayCardEvent;

public class PlayCardProcessor implements EventProcessor<PlayCardEvent>{

    private final Game game;

    public PlayCardProcessor(Game game) {
        this.game = game;
    }

    @Override
    public void process(PlayCardEvent event) {

        Card card = event.getCard();
        String sourcePlayerId = event.getSourcePlayerId();
        Player playerInTheGame = game.getPlayers().get(sourcePlayerId);
        playerInTheGame.getCards().remove(card);
        game.getCardsInTheGame().add(card);
        game.getEvents().add(event);
    }
}