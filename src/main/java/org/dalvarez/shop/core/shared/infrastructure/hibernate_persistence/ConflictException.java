package org.dalvarez.shop.core.shared.infrastructure.hibernate_persistence;

import org.dalvarez.shop.core.shared.domain.util.StringUtils;

public class ConflictException extends RuntimeException {

    private static final String UUID_FIELD = "uuid";

    private static final String FIELD_WILDCART = "{}";

    private static final String CLASS_WILDCART = "[]";

    private static final String MESSAGE_FORMAT = CLASS_WILDCART + " <%s=" + FIELD_WILDCART + "> already exists";

    public ConflictException(final String message) {
        super(message);
    }

    public ConflictException(final Class<?> clazz,
                             final String uuid) {
        super(getUuidMessage(clazz, uuid));
    }

    public static String getUuidMessage(final Class<?> clazz,
                                        final String uuid) {
        return getMessage(clazz, uuid);
    }

    private static <T> String getMessage(final Class<?> clazz,
                                         final T fieldValue) {
        return String.format(
                MESSAGE_FORMAT
                        .replace(CLASS_WILDCART, clazz.getSimpleName())
                        .replace(FIELD_WILDCART, StringUtils.STRING_FORMAT),
                ConflictException.UUID_FIELD, fieldValue
        );
    }

}
