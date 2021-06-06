package org.dalvarez.shop.core.shared.domain.criteria.filter;

import org.dalvarez.shop.core.shared.domain.exception.IllegalEnumValueException;
import org.dalvarez.shop.core.shared.domain.util.StringUtils;

import java.util.Optional;

public enum FiltersBooleanOperator {

    AND(FiltersBooleanOperator.AND_VALUE),
    OR(FiltersBooleanOperator.OR_VALUE);

    private static final String AND_VALUE = "AND";

    private static final String OR_VALUE = "OR";

    private final String value;

    FiltersBooleanOperator(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Boolean isAND() {
        return this.equals(AND);
    }

    public Boolean isOR() {
        return this.equals(OR);
    }

    public static FiltersBooleanOperator fromValue(final String value) {
        switch (Optional.ofNullable(value)
                        .orElse(StringUtils.EMPTY)
                        .toUpperCase()) {
            case AND_VALUE:
            case StringUtils.EMPTY:
                return FiltersBooleanOperator.AND;
            case OR_VALUE:
                return FiltersBooleanOperator.OR;
            default:
                throw new IllegalEnumValueException(FiltersBooleanOperator.class, value);
        }
    }

}
