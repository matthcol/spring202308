package org.example.movieapi.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.stream.Collectors;

public class HttpExceptions {

    public static <T> ResponseStatusException notFoundException(String entityName, T id) {
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                MessageFormat.format("{0} not found with id <{1}>", entityName, id));
    }

    public static <T,U> ResponseStatusException notFoundException(
            String mainEntityName, T idMainEntityName,
            String associatedEntityName, U... idAssociatedEntities)
    {
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                MessageFormat.format("{0} with id <{1}> or {2} with id(s) <{3}> not found",
                        mainEntityName,
                        idMainEntityName,
                        associatedEntityName,
                        Arrays.stream(idAssociatedEntities)
                                .map(Object::toString)
                                .collect(Collectors.joining(", "))
                ));
    }
}
