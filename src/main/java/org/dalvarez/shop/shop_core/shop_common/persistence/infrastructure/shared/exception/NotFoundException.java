package org.dalvarez.shop.shop_core.shop_common.persistence.infrastructure.shared.exception;

import org.dalvarez.shop.shop_core.shop_common.shared.domain.util.StringUtils;
import org.dalvarez.shop.shop_core.shop_common.shared.domain.value_object.id.Identifier;

public class NotFoundException extends RuntimeException {

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
                                      final Identifier id) {
        return getFieldMessage(
                StringUtils.STRING_FORMAT,
                clazz,
                ID_FIELD,
                id
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
