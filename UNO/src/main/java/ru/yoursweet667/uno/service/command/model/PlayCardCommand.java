package ru.yoursweet667.uno.service.command.model;

import ru.yoursweet667.uno.service.model.Card;

public class PlayCardCommand {

    private final String playerId;

    private final CommandType commandType;

    private final Card card;

    public PlayCardCommand(String playerId, Card card) {
        this.playerId = playerId;
        this.commandType = CommandType.PLAY_CARD;
        this.card = card;
    }

    public String getPlayerId() {
        return playerId;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public Card getCard() {
        return card;
    }
}
