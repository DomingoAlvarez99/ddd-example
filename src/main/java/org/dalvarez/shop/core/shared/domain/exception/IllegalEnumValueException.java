package org.dalvarez.shop.core.shared.domain.exception;

public class IllegalEnumValueException extends IllegalArgumentException {

    private static final String FORMAT_MESSAGE = "Unable to find any <%s> from this value <%s>";

    private static final String UNKNOWN_FIELD_NAME = "?";

    public IllegalEnumValueException(final Class<?> clazz,
                                     final String value) {
        super(String.format(
                FORMAT_MESSAGE,
                clazz.getSimpleName(),
                value
        ));
    }

}
