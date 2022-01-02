package org.dalvarez.ddd_example.shared.infrastructure.rest_api.exception_handler;

import org.dalvarez.ddd_example.shared.domain.criteria.NonUniqueResultException;
import org.dalvarez.ddd_example.shared.domain.criteria.filter.WrongFilterException;
import org.dalvarez.ddd_example.shared.domain.log.Logger;
import org.dalvarez.ddd_example.shared.infrastructure.shared.exception.ConflictException;
import org.dalvarez.ddd_example.shared.infrastructure.shared.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.PersistenceException;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestControllerAdvice
public class ApiGlobalExceptionHandler {

    private final Logger log;

    public ApiGlobalExceptionHandler(final Logger log) {
        this.log = log;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFoundException(final Exception ex) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            HttpRequestMethodNotSupportedException.class,
            MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class,
            WrongFilterException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleBadRequestException(final Exception ex) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            ConflictException.class,
            PersistenceException.class,
            NonUniqueResultException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleConflictException(final Exception ex) {
        return buildErrorResponse(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleInternalServerErrorException(final Exception ex) {
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(final Exception ex,
                                                             final HttpStatus httpStatus) {
        return new ResponseEntity<>(errorResponse(ex, httpStatus), httpStatus);
    }

    private ErrorResponse errorResponse(final Exception ex,
                                        final HttpStatus httpStatus) {
        final String causeMessage = ex.getMessage();
        final int httpCode = httpStatus.value();
        final String httpName = httpStatus.name();
        final Optional<StackTraceElement> stackTraceElement = Arrays.stream(ex.getStackTrace())
                                                                    .findFirst();
        final AtomicReference<String> clazz = new AtomicReference<>(null);
        final AtomicReference<String> method = new AtomicReference<>(null);
        final AtomicReference<Integer> line = new AtomicReference<>(null);

        stackTraceElement.ifPresent(traceElement -> {
            clazz.set(traceElement.getClassName());
            method.set(traceElement.getMethodName());
            line.set(traceElement.getLineNumber());
        });

        log.error(
                "Exception captured: [cause {}, status {}, class {}, method {}, line {}]",
                causeMessage,
                httpCode,
                clazz.get(),
                method.get(),
                line.get(),
                ex
        );

        return ErrorResponse.of(
                httpCode,
                httpName,
                causeMessage
        );
    }

}
