package org.dalvarez.shop.core.shared.domain.exception;

public class UnknownFieldNameException extends IllegalArgumentException {

    private static final String FORMAT_MESSAGE = "Unable to find the field <%s> in the class <%s>";

    public UnknownFieldNameException(final Class<?> clazz,
                                     final String value) {
        super(String.format(
                FORMAT_MESSAGE,
                value,
                clazz.getSimpleName()
        ));
    }

}
