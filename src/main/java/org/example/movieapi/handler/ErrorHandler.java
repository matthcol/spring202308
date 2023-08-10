package org.example.movieapi.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
 * Articles:
 * https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
 * https://www.baeldung.com/exception-handling-for-rest-with-spring
 *
 *  extends ResponseEntityExceptionHandler // if you want to override default handlers (validation, methods not supported, not acceptable, ...)
 *      + @override protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request)
 *  @Order(Ordered.HIGHEST_PRECEDENCE) // to control advices order
 *
 * handler can return void (use @ResponseStatus), String, ErrorDto, EntityResponse<String|ErrorDto>
 * handler can use internal exception and request causing the problem by adding parameters (Exception exception, WebRequest request)
 */
@ControllerAdvice
public class ErrorHandler  {

    @ResponseStatus(value = HttpStatus.CONFLICT, reason="Data Integrity Violation")
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void handleDaoDataIntegrityViolationException() {
        // response is handled by annotation @ResponseStatus
    }

    // NB: @ResponseStatus is not mandatory with ProblemDetail.forStatus
    // NB: ResponseStatus can be included in a ResponseEntity too
    //    //@ResponseStatus(value = HttpStatus.CONFLICT, reason="Data Integrity Violation")
    //    @ExceptionHandler(DataAccessException.class)
    //    public ProblemDetail handleDaoDataIntegrityViolationException() {
    //        // NB: you can use any DTO here
    //        var pb = ProblemDetail.forStatus(HttpStatus.CONFLICT);
    //        pb.setProperty("message", "Data Integrity Violation");
    //        return pb;
    //        // response is handled by annotation @ResponseStatus
    //    }

}
