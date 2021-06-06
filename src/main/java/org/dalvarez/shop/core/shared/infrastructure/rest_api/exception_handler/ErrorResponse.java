package org.dalvarez.shop.core.shared.infrastructure.rest_api.exception_handler;

public class ErrorResponse {

    private final Integer httpStatus;

    private final String type;

    private final String message;

    public ErrorResponse() {
        httpStatus = null;
        type = null;
        message = null;
    }

    public ErrorResponse(final Integer httpStatus,
                         final String type,
                         final String message) {
        this.httpStatus = httpStatus;
        this.type = type;
        this.message = message;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

}
