package org.dalvarez.ddd_example.shared.infrastructure.rest_api.exception_handler;

import com.fasterxml.jackson.annotation.JsonGetter;

public class ErrorResponse {

    private final Integer httpStatus;

    private final String type;

    private final String message;

    private ErrorResponse() {
        httpStatus = null;
        type = null;
        message = null;
    }

    private ErrorResponse(final Integer httpStatus,
                          final String type,
                          final String message) {
        this.httpStatus = httpStatus;
        this.type = type;
        this.message = message;
    }

    public static ErrorResponse of(final Integer httpStatus,
                                   final String type,
                                   final String message) {
        return new ErrorResponse(httpStatus, type, message);
    }

    @JsonGetter
    public Integer httpStatus() {
        return httpStatus;
    }

    @JsonGetter
    public String type() {
        return type;
    }

    @JsonGetter
    public String message() {
        return message;
    }

}
