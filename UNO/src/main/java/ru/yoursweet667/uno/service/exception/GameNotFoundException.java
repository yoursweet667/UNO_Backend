package ru.yoursweet667.uno.service.exception;

public class GameNotFoundException extends RuntimeException{

    public GameNotFoundException(String message) {
        super(message);
    }
}