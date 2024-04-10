package com.footballclub.core.exception;

public class ClubNotFoundException extends NotFoundException {
    public ClubNotFoundException(String message) {
        super(message);
    }

    public ClubNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClubNotFoundException(Throwable cause) {
        super(cause);
    }
}
