package org.dalvarez.shop.core.shared.infrastructure.hibernate_persistence;

import org.dalvarez.shop.core.shared.domain.util.StringUtils;

public class NotFoundException extends RuntimeException {

    private static final String UUID_FIELD = "uuid";

    private static final String ID_FIELD = "id";

    private static final String FIELD_WILDCART = "{}";

    private static final String CLASS_WILDCART = "[]";

    private static final String FIELD_MESSAGE_FORMAT = CLASS_WILDCART + " <%s=" + FIELD_WILDCART + "> not found";

    private static final String DEFAULT_MESSAGE_FORMAT = CLASS_WILDCART + " not found";

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Class<?> clazz) {
        super(getDefaultMessage(clazz));
    }

    public static String getIdMessage(final Class<?> clazz,
                                      final Long id) {
        return getFieldMessage(
                StringUtils.INTEGER_FORMAT,
                clazz,
                ID_FIELD,
                id
        );
    }

    public static String getUuidMessage(final Class<?> clazz,
                                        final String uuid) {
        return getFieldMessage(
                StringUtils.STRING_FORMAT,
                clazz,
                UUID_FIELD,
                uuid
        );
    }

    private static <T> String getFieldMessage(final String format,
                                              final Class<?> clazz,
                                              final String field,
                                              final T fieldValue) {
        return String.format(
                FIELD_MESSAGE_FORMAT
                        .replace(CLASS_WILDCART, clazz.getSimpleName())
                        .replace(FIELD_WILDCART, format),
                field,
                fieldValue
        );
    }

    public static String getDefaultMessage(final Class<?> clazz) {
        return DEFAULT_MESSAGE_FORMAT.replace(
                CLASS_WILDCART,
                clazz.getSimpleName()
        );
    }

}
