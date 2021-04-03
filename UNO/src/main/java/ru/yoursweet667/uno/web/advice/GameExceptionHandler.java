package ru.yoursweet667.uno.web.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.yoursweet667.uno.dataaccess.game.exception.InvalidGameStorageRequestException;
import ru.yoursweet667.uno.service.exception.GameAlreadyExistsException;
import ru.yoursweet667.uno.service.exception.GameNotFoundException;
import ru.yoursweet667.uno.service.exception.PlayerNotFoundException;
import ru.yoursweet667.uno.web.model.Error;

@ControllerAdvice
public class GameExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidGameStorageRequestException.class)
    public ResponseEntity<Object> handle(InvalidGameStorageRequestException exception, WebRequest webRequest) {

        Error error = new Error(exception.getMessage());
        return handleExceptionInternal(exception, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<Object> handle(GameNotFoundException exception, WebRequest webRequest) {

        Error error = new Error(exception.getMessage());
        return handleExceptionInternal(exception, error, new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler(GameAlreadyExistsException.class)
    public ResponseEntity<Object> handle(GameAlreadyExistsException exception, WebRequest webRequest) {

        Error error = new Error(exception.getMessage());
        return handleExceptionInternal(exception, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<Object> handle(PlayerNotFoundException exception, WebRequest webRequest) {

        Error error = new Error(exception.getMessage());
        return handleExceptionInternal(exception, error, new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }
}
