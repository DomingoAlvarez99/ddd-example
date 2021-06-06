package org.dalvarez.shop.core.shared.infrastructure.rest_api.exception_handler;

import org.dalvarez.shop.core.shared.domain.exception.BadRequestException;
import org.dalvarez.shop.core.shared.domain.log.Logger;
import org.dalvarez.shop.core.shared.infrastructure.hibernate_persistence.ConflictException;
import org.dalvarez.shop.core.shared.infrastructure.hibernate_persistence.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.PersistenceException;

@RestControllerAdvice
public class ApiExceptionHandler {

    private static final String UNKNOWN = "Unknown";

    private final Logger log;

    public ApiExceptionHandler(final Logger log) {
        this.log = log;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFoundException(final Exception ex) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            BadRequestException.class,
            IllegalArgumentException.class,
            HttpRequestMethodNotSupportedException.class,
            MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleBadRequestException(final Exception ex) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            ConflictException.class,
            PersistenceException.class
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
        return new ResponseEntity<>(getErrorResponse(ex, httpStatus), httpStatus);
    }

    private ErrorResponse getErrorResponse(final Exception ex,
                                           final HttpStatus httpStatus) {
        final String causeMessage = ex.getMessage();
        final int httpCode = httpStatus.value();
        final String httpName = httpStatus.name();
        final String clazz = getClassName(ex);
        final String method = getMethodName(ex);
        final int line = getLine(ex);

        log.error(
                "Exception captured:\n [cause {},\n status {},\n class {},\n method {},\n line {}]",
                causeMessage,
                httpCode,
                clazz,
                method,
                line,
                ex
        );

        return new ErrorResponse(
                httpCode,
                httpName,
                causeMessage
        );
    }

    private String getMethodName(final Exception ex) {
        if (hasAnyTrace(ex))
            return ex.getStackTrace()[0].getMethodName();

        return UNKNOWN;
    }

    private Integer getLine(final Exception ex) {
        if (hasAnyTrace(ex))
            return ex.getStackTrace()[0].getLineNumber();

        return 0;
    }

    private Boolean hasAnyTrace(final Exception ex) {
        return ex.getStackTrace().length > 0;
    }

    private String getClassName(final Exception ex) {
        try {
            return Class.forName(ex.getStackTrace()[0].getClassName())
                        .getName();

        } catch (final Exception ignored) {
            return UNKNOWN;
        }
    }

}
