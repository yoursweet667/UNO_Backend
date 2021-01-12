package ru.yoursweet667.uno.service.event.validator;

import ru.yoursweet667.uno.service.model.*;
import ru.yoursweet667.uno.service.model.event.TakeCardsEvent;

/**
 * gameState испльзуется ситуативно,для одного случая необходиом состояние INITIALISING,
 * для иного GAME_ACTIVE
 */

/*
    0. Проверить состояние игры
    1. Если состояние игры INITIALISING - узнать кол-во карт игрока и event
    2. В противном случае - проверить чей сейчас ход
    и проверить карты игрока на возможность хода
     */

public class TakeCardsValidator implements EventValidator<TakeCardsEvent> {

    private final Game game;
    private final Player player;

    public TakeCardsValidator(Game game, Player player) {
        this.game = game;
        this.player = player;
    }

    @Override
    public void validate(TakeCardsEvent event) {

        if (game.getGameState() == GameState.INITIALISING) {
            validatePlayerHasNoCards(event.getPlayer());
            validateEventHasSevenCards(event);
        } else if (game.getGameState() == GameState.GAME_ACTIVE) {
            validatePlayerPlaysNext(event.getPlayer());
            validatePlayerDoesNotHaveACardToPlay();
        } else throw new IllegalArgumentException("Incorrect Game State");
    }

    public void validatePlayerHasNoCards(Player player) {
        int requiredNumberOfCards = 0;
        int currentNumberOfCards = player.getCards().size();
        if (requiredNumberOfCards != currentNumberOfCards) {
            throw new IllegalArgumentException("Number Of Cards Isn't 0");
        }
    }

    public void validateEventHasSevenCards(TakeCardsEvent event) {
        int requiredNumberOfCards = 7;
        int currentNumberOfCards = event.getCards().size();
        if(requiredNumberOfCards != currentNumberOfCards) {
            throw new IllegalArgumentException("Number Of Cards Isn't 7");
        }
    }

    public void validatePlayerPlaysNext(Player player) {
        Player nextPlayer = game.getNextPlayer()
                .orElseThrow(() -> new IllegalStateException("Player Isn't Found"));
        if (player.equals(nextPlayer)) {
            throw new IllegalArgumentException("It Isn't " + player + " Turn");
        }
    }

    public void validatePlayerDoesNotHaveACardToPlay() {

        int playerCardsSize = player.getCards().size();
        Card lastCardInTheGame = game.getLastCardInTheGame()
                .orElseThrow(() -> new IllegalStateException("Player Isn't Found"));

        for (int i = 0; i < playerCardsSize ; i++) {
            Card playerCard = player.getCards().get(i);

            CardColour lastCardInTheGameCardColour = lastCardInTheGame.getColour();
            Integer lastCardInTheGameCardNumber = lastCardInTheGame.getNumber();
            CardColour playerCardColour = playerCard.getColour();
            Integer playerCardNumber = playerCard.getNumber();
            CardType playerCardType = playerCard.getType();

            if (!lastCardInTheGameCardColour.equals(playerCardColour) &
                    !lastCardInTheGameCardNumber.equals(playerCardNumber) &
                    !playerCardType.equals(CardType.CHANGE_COLOUR) &
                    !playerCardType.equals(CardType.PLUS_4)) {
                throw new IllegalArgumentException("Player Card Doesn't Equals Required Params");
            }
        }
    }
}