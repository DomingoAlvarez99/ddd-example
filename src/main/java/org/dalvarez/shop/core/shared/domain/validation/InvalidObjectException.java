package org.dalvarez.shop.core.shared.domain.validation;

import org.dalvarez.shop.core.shared.domain.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class InvalidObjectException extends Exception {

    private static final String LEADING_ERRORS_DELIMITER = "[";

    private static final String ENDING_ERRORS_DELIMITER = "]";

    static final String FORMAT = "Class <%s> has fields with invalid values "
            + LEADING_ERRORS_DELIMITER + "%s" + ENDING_ERRORS_DELIMITER;

    private static final String ERROR_DELIMITER = "<''>";

    public InvalidObjectException(final Class<?> clazz,
                                  final List<String> validationErrors) {
        super(String.format(
                FORMAT,
                clazz.getSimpleName(),
                String.join(ERROR_DELIMITER, validationErrors)
        ));
    }

    public static List<String> asListOfErrors(final String validationErrors) {
        final String errorsDelimiterRe = StringUtils.escapePattern(LEADING_ERRORS_DELIMITER);
        final int messageDescriptionPart = 1;

        return Arrays.asList(Arrays.stream(validationErrors.split(errorsDelimiterRe))
                                   .skip(messageDescriptionPart)
                                   .collect(Collectors.joining())
                                   .replace(ENDING_ERRORS_DELIMITER, StringUtils.EMPTY)
                                   .split(ERROR_DELIMITER));
    }

}
