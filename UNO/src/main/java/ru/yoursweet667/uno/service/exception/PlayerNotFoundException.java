package ru.yoursweet667.uno.service.exception;

public class PlayerNotFoundException extends RuntimeException{

    public PlayerNotFoundException(String message) {
        super(message);
    }
}
