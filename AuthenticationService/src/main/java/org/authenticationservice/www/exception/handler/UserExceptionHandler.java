package org.authenticationservice.www.exception.handler;

import org.authenticationservice.www.exception.UserAlreadyExistException;
import org.authenticationservice.www.exception.UserNotFoundException;
import org.authenticationservice.www.exception.UserPasswordIncorrectException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(
            UserNotFoundException ex, WebRequest request
    ) {
        logger.error(ex.getMessage(), ex);
        String body = "Your user could not be found";
        return handleExceptionInternal(ex, body ,new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Object> handleUserAlreadyExistException(
            UserAlreadyExistException ex, WebRequest request
    ) {
        logger.error(ex.getMessage(), ex);
        String body = "Your user already exists";
        return handleExceptionInternal(ex, body ,new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(UserPasswordIncorrectException.class)
    public ResponseEntity<Object> handleUserPasswordIncorrectException(
            UserPasswordIncorrectException ex, WebRequest request
    ) {
        logger.error(ex.getMessage(), ex);
        String body = "Incorrect password";
        return handleExceptionInternal(ex, body ,new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
