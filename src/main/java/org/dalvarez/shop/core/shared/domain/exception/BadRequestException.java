package org.dalvarez.shop.core.shared.domain.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(final String detail) {
        super(detail);
    }

    public BadRequestException(final Throwable throwable) {
        super(throwable);
    }

}
