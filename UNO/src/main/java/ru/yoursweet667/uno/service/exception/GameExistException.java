package ru.yoursweet667.uno.service.exception;

public class GameExistException extends RuntimeException{

    public GameExistException(String message) {
        super(message);
    }
}