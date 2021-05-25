package ru.yoursweet667.uno.service.exception;

public class GameAlreadyExistsException extends RuntimeException{

    public GameAlreadyExistsException(String message) {
        super(message);
    }
}