package com.rudy.bibliotheque.mbook.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ProhibitedActionException extends RuntimeException{
    public ProhibitedActionException(String message) {
        super(message);
    }
}
