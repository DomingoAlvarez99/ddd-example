package org.dalvarez.shop.core.shared.domain.validation;

public final class ValidationNotPassedException extends Exception {

    static final String FORMAT = "Field <%s=%s> has not passed the validator <%s> cause %s";

    public ValidationNotPassedException(final String fieldName,
                                        final Object fieldValue,
                                        final String cause,
                                        final Class<?> fieldValidator) {
        super(String.format(
                FORMAT,
                fieldName,
                fieldValue,
                fieldValidator.getSimpleName(),
                cause
        ));
    }

}
