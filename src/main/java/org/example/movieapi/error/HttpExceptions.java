package org.example.movieapi.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;

public class HttpExceptions {

    public static <T> ResponseStatusException notFoundException(String entityName, T id) {
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                MessageFormat.format("{0} not found with id <{1}>", entityName, id));
    }
}
