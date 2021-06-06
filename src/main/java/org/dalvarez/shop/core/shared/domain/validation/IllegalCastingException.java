package org.dalvarez.shop.core.shared.domain.validation;

public final class IllegalCastingException extends RuntimeException {

    static final String FORMAT = "The object <%s,%s> could not be casted to %s.";

    public IllegalCastingException(final Object value,
                                   final Class<?> clazz) {
        super(String.format(
                FORMAT,
                value,
                value.getClass()
                     .getSimpleName(),
                clazz.getSimpleName()
        ));
    }

}
