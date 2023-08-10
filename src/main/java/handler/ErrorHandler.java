package handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
 * Articles:
 * https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
 * https://www.baeldung.com/exception-handling-for-rest-with-spring
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    // NB: possibility to override handler already defined
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        return super.handleMethodArgumentNotValid(ex, headers, status, request);
//    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDaoDataIntegrityViolationException(
            Exception exception, WebRequest request)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Data Integrity Violation");
    }
}
