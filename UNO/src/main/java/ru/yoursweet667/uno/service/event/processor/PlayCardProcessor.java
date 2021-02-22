package ru.yoursweet667.uno.service.event.processor;

import ru.yoursweet667.uno.service.model.Card;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.event.PlayCardEvent;

public class PlayCardProcessor extends BaseEventProcessor<PlayCardEvent> {

    @Override
    void doProcess(PlayCardEvent event, Game game) {
        Card card = event.getCard();
        String sourcePlayerId = event.getSourcePlayerId();
        Player playerInTheGame = game.getPlayers().get(sourcePlayerId);

        for (Card playerCard : playerInTheGame.getCards()) {
            if (playerCard.equals(card)) {
                game.getCardsInTheGame().add(playerCard);
                break;
            }
        }
        playerInTheGame.getCards().remove(card);
    }
}