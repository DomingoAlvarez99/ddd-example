package org.dalvarez.shop.shop_core.shop_common.persistence.infrastructure.shared.exception;

import org.dalvarez.shop.shop_core.shop_common.shared.domain.util.StringUtils;

public class ConflictException extends RuntimeException {

    private static final String ID_FIELD = "id";

    private static final String FIELD_WILDCART = "{}";

    private static final String CLASS_WILDCART = "[]";

    private static final String MESSAGE_FORMAT = CLASS_WILDCART + " <%s=" + FIELD_WILDCART + "> already exists";

    public ConflictException(final String message) {
        super(message);
    }

    public ConflictException(final Class<?> clazz,
                             final String id) {
        super(getIdMessage(clazz, id));
    }

    public static String getIdMessage(final Class<?> clazz,
                                      final String id) {
        return getMessage(clazz, id);
    }

    private static <T> String getMessage(final Class<?> clazz,
                                         final T fieldValue) {
        return String.format(
                MESSAGE_FORMAT
                        .replace(CLASS_WILDCART, clazz.getSimpleName())
                        .replace(FIELD_WILDCART, StringUtils.STRING_FORMAT),
                ConflictException.ID_FIELD, fieldValue
        );
    }

}
