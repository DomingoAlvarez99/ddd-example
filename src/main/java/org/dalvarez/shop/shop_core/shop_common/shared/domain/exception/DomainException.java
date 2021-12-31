package org.dalvarez.shop.shop_core.shop_common.shared.domain.exception;

public abstract class DomainException extends RuntimeException {

    private final String errorCode;

    private final String errorMessage;

    public DomainException(String errorCode,
                           String errorMessage) {
        super(errorMessage);

        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String errorCode() {
        return errorCode;
    }

    public String errorMessage() {
        return errorMessage;
    }

}