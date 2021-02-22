package ru.yoursweet667.uno.service.event.validator;

import ru.yoursweet667.uno.service.model.*;
import ru.yoursweet667.uno.service.model.event.TakeCardsEvent;

/**
 * gameState испльзуется ситуативно, для одного случая необходиом состояние INITIALISING,
 * для иного START_TURN
 */


public class TakeCardsValidator implements EventValidator<TakeCardsEvent> {

    @Override
    public void validate(TakeCardsEvent event, Game game) {
        if (game.getGameState() == GameState.INITIALISING) {
            validatePlayerHasNoCards(event.getPlayer());
            validateEventHasSevenCards(event);
        } else if (game.getGameState() == GameState.START_TURN) {
            validatePlayerPlaysNext(event.getPlayer(), game);
            validatePlayerDoesNotHaveACardToPlay(event.getPlayer(), game);
        } else throw new IllegalArgumentException("Incorrect Game State");
    }

    private void validatePlayerHasNoCards(Player player) {
        int requiredNumberOfCards = 0;
        int currentNumberOfCards = player.getCards().size();
        if (requiredNumberOfCards != currentNumberOfCards) {
            throw new IllegalArgumentException("Number Of Cards Isn't 0");
        }
    }

    private void validateEventHasSevenCards(TakeCardsEvent event) {
        int requiredNumberOfCards = 7;
        int currentNumberOfCards = event.getCards().size();
        if (requiredNumberOfCards != currentNumberOfCards) {
            throw new IllegalArgumentException("Number Of Cards Isn't 7");
        }
    }

    private void validatePlayerPlaysNext(Player player, Game game) {
        Player nextPlayer = game.getNextPlayer()
                .orElseThrow(() -> new IllegalArgumentException("Player Isn't Found"));
        if (player.equals(nextPlayer)) {
            throw new IllegalArgumentException("It Isn't " + player + " Turn");
        }
    }

    private void validatePlayerDoesNotHaveACardToPlay(Player player, Game game) {

        int playerCardsSize = player.getCards().size();
        Card lastCardInTheGame = game.getLastCardInTheGame()
                .orElseThrow(() -> new IllegalStateException("Player Isn't Found"));

        for (int i = 0; i < playerCardsSize; i++) {
            Card playerCard = player.getCards().get(i);

            CardColour lastCardInTheGameCardColour = lastCardInTheGame.getColour();
            CardColour playerCardColour = playerCard.getColour();
            CardType playerCardType = playerCard.getType();

            if (lastCardInTheGameCardColour != playerCardColour &
                    playerCardType != lastCardInTheGame.getType()) {
                throw new IllegalArgumentException("Player Card Doesn't Equals Required Params");
            }
        }
    }
}