package ru.yoursweet667.uno.service.exception;

public class PlayerExistException extends RuntimeException {

    public PlayerExistException(String message) {
        super(message);
    }
}
