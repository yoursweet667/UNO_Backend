package ru.yoursweet667.uno.web.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.yoursweet667.uno.dataaccess.game.exception.InvalidGameStorageRequestException;
import ru.yoursweet667.uno.web.model.Error;

@ControllerAdvice
public class GameExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidGameStorageRequestException.class)
    public ResponseEntity<Object> handle(InvalidGameStorageRequestException exception, WebRequest webRequest) {

        Error error = new Error(exception.getMessage());
        return handleExceptionInternal(exception, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }
}
