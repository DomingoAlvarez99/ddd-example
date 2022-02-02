package org.dalvarez.ddd_example.shared.infrastructure.shared.exception;

import org.dalvarez.ddd_example.shared.domain.value_object.id.Identifier;

public class NotFoundException extends RuntimeException {

    private static final String ID_FIELD = "id";

    private static final String FIELD_WILDCARD = "{}";

    private static final String CLASS_WILDCARD = "[]";

    private static final String FIELD_MESSAGE_FORMAT = CLASS_WILDCARD + " <%s=" + FIELD_WILDCARD + "> not found";

    private static final String DEFAULT_MESSAGE_FORMAT = CLASS_WILDCARD + " not found";

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Class<?> clazz) {
        super(defaultMessage(clazz));
    }

    public static NotFoundException build(final Class<?> clazz,
                                          final Identifier id) {
        return new NotFoundException(idMessage(clazz, id));
    }

    public static String idMessage(final Class<?> clazz,
                                   final Identifier id) {
        return fieldMessage(
                "%s",
                clazz,
                ID_FIELD,
                id
        );
    }

    private static <T> String fieldMessage(final String format,
                                           final Class<?> clazz,
                                           final String field,
                                           final T fieldValue) {
        return String.format(
                FIELD_MESSAGE_FORMAT
                        .replace(CLASS_WILDCARD, clazz.getSimpleName())
                        .replace(FIELD_WILDCARD, format),
                field,
                fieldValue
        );
    }

    public static String defaultMessage(final Class<?> clazz) {
        return DEFAULT_MESSAGE_FORMAT.replace(
                CLASS_WILDCARD,
                clazz.getSimpleName()
        );
    }

}
