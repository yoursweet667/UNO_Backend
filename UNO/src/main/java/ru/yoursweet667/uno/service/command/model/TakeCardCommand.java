package ru.yoursweet667.uno.service.command.model;

import java.util.Objects;

public class TakeCardCommand {

    private final String playerId;

    private final CommandType commandType;

    public TakeCardCommand(String playerId) {
        this.playerId = playerId;
        this.commandType = CommandType.TAKE_CARD;
    }

    public String getPlayerId() {
        return playerId;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TakeCardCommand that = (TakeCardCommand) o;
        return Objects.equals(playerId, that.playerId) &&
                commandType == that.commandType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, commandType);
    }
}
