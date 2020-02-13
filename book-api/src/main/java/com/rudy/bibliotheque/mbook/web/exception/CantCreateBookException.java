package com.rudy.bibliotheque.mbook.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CantCreateBookException extends RuntimeException {
    public CantCreateBookException() {
        super("Impossible de créer le livre");
    }

    public CantCreateBookException(String message) {
        super(message);
    }
}
