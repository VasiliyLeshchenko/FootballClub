package org.authenticationservice.www.exception;

public class UserPasswordIncorrectException extends RuntimeException {
    public UserPasswordIncorrectException(String message) {
        super(message);
    }
}
