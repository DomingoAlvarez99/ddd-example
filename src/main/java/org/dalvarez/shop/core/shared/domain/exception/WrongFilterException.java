package org.dalvarez.shop.core.shared.domain.exception;

import org.dalvarez.shop.core.shared.domain.criteria.filter.Filter;

public class WrongFilterException extends IllegalArgumentException {

    private static final String FORMAT_MESSAGE = "The filter <%s> could not be processed, correct format <%s>";

    public WrongFilterException(final String filter) {
        super(String.format(
                FORMAT_MESSAGE,
                filter,
                Filter.FILTER_REGEX
        ));
    }

}
