package org.dalvarez.ddd_example.shared.domain.criteria.filter;

import org.dalvarez.ddd_example.shared.domain.exception.IllegalEnumValueException;

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

    public String value() {
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
                        .orElse("")
                        .toUpperCase()) {
            case AND_VALUE:
            case "":
                return FiltersBooleanOperator.AND;
            case OR_VALUE:
                return FiltersBooleanOperator.OR;
            default:
                throw new IllegalEnumValueException(FiltersBooleanOperator.class, value);
        }
    }

}
