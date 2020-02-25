package com.rudy.bibliotheque.webui.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomErrorDecoder implements ErrorDecoder {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        logger.error("Status code " + response.status() + ", methodKey = " + methodKey + ", body = " + response.body());
        if (response.status() >= 400 && response.status() <= 499) {
            return new ResponseStatusException(HttpStatus.valueOf(response.status()), response.body().toString());
        }
        if (response.status() >= 500 && response.status() <= 599) {
            return new ResponseStatusException(HttpStatus.valueOf(response.status()), response.body().toString());
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }
}
