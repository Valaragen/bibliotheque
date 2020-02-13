package com.rudy.bibliotheque.mbook.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        super("Aucun livre n'a été trouvé");
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}
