package ru.yoursweet667.uno.service.command.model;

import ru.yoursweet667.uno.service.model.Card;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayCardCommand that = (PlayCardCommand) o;
        return Objects.equals(playerId, that.playerId) &&
                commandType == that.commandType &&
                Objects.equals(card, that.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, commandType, card);
    }
}
