package org.dalvarez.shop.shop_core.shop_common.shared.infrastructure.validation;

public class BadRequestException extends RuntimeException {

    public BadRequestException(final String detail) {
        super(detail);
    }

    public BadRequestException(final Throwable throwable) {
        super(throwable);
    }

}
