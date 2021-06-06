package org.dalvarez.shop.core.shared.domain.criteria.filter;

import org.dalvarez.shop.core.shared.domain.exception.IllegalEnumValueException;
import org.dalvarez.shop.core.shared.domain.util.StringUtils;

import java.util.Optional;

public enum FilterOperator {

    EQUAL(FilterOperator.EQUAL_VALUE),
    NOT_EQUAL(FilterOperator.NOT_EQUAL_VALUE),
    GREATER_THAN(FilterOperator.GREATER_THAN_VALUE),
    LESS_THAN(FilterOperator.LESS_THAN_VALUE),
    CONTAINS(FilterOperator.CONTAINS_VALUE),
    NOT_CONTAINS(FilterOperator.NOT_CONTAINS_VALUE);

    private static final String EQUAL_VALUE = "EQ";

    private static final String NOT_EQUAL_VALUE = "NE";

    private static final String GREATER_THAN_VALUE = "GT";

    private static final String LESS_THAN_VALUE = "LT";

    private static final String CONTAINS_VALUE = "CO";

    private static final String NOT_CONTAINS_VALUE = "NC";

    private final String operator;

    FilterOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return operator;
    }

    public static FilterOperator fromValue(final String value) {
        switch (Optional.ofNullable(value)
                        .orElse(StringUtils.EMPTY)
                        .toUpperCase()) {
            case EQUAL_VALUE:
            case StringUtils.EMPTY:
                return FilterOperator.EQUAL;
            case NOT_EQUAL_VALUE:
                return FilterOperator.NOT_EQUAL;
            case GREATER_THAN_VALUE:
                return FilterOperator.GREATER_THAN;
            case LESS_THAN_VALUE:
                return FilterOperator.LESS_THAN;
            case CONTAINS_VALUE:
                return FilterOperator.CONTAINS;
            case NOT_CONTAINS_VALUE:
                return FilterOperator.NOT_CONTAINS;
            default:
                throw new IllegalEnumValueException(FilterOperator.class, value);
        }
    }

}
