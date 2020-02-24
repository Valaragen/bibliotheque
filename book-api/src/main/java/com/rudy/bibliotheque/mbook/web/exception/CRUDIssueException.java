package com.rudy.bibliotheque.mbook.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CRUDIssueException extends RuntimeException {
    public CRUDIssueException(String message) {
        super(message);
    }
}
