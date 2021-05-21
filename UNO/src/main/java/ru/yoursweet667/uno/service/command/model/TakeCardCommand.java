package ru.yoursweet667.uno.service.command.model;

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
}
