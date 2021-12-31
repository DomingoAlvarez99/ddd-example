package org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.filter;

import org.dalvarez.shop.shop_core.shop_common.shared.domain.exception.IllegalEnumValueException;
import org.dalvarez.shop.shop_core.shop_common.shared.domain.util.StringUtils;

import java.util.Optional;

public enum FilterOperator {

    EQUAL(FilterOperator.EQUAL_VALUE),
    I_EQUAL(FilterOperator.I_EQUAL_VALUE),
    NOT_EQUAL(FilterOperator.NOT_EQUAL_VALUE),
    GREATER_THAN(FilterOperator.GREATER_THAN_VALUE),
    GREATER_OR_EQUAL_THAN(FilterOperator.GREATER_OR_EQUAL_THAN_VALUE),
    LESS_THAN(FilterOperator.LESS_THAN_VALUE),
    LESS_OR_EQUAL_THAN(FilterOperator.LESS_OR_EQUAL_THAN_VALUE),
    IN(FilterOperator.IN_VALUE),
    CONTAINS(FilterOperator.CONTAINS_VALUE),
    I_CONTAINS(FilterOperator.I_CONTAINS_VALUE),
    NOT_CONTAINS(FilterOperator.NOT_CONTAINS_VALUE);

    private static final String EQUAL_VALUE = "EQ";

    private static final String I_EQUAL_VALUE = "IEQ";

    private static final String NOT_EQUAL_VALUE = "NEQ";

    private static final String GREATER_THAN_VALUE = "GT";

    private static final String GREATER_OR_EQUAL_THAN_VALUE = "GTE";

    private static final String LESS_THAN_VALUE = "LT";

    private static final String LESS_OR_EQUAL_THAN_VALUE = "LTE";

    private static final String IN_VALUE = "IN";

    private static final String CONTAINS_VALUE = "CO";

    private static final String I_CONTAINS_VALUE = "ICO";

    private static final String NOT_CONTAINS_VALUE = "NCO";

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
            case I_EQUAL_VALUE:
                return FilterOperator.I_EQUAL;
            case NOT_EQUAL_VALUE:
                return FilterOperator.NOT_EQUAL;
            case GREATER_THAN_VALUE:
                return FilterOperator.GREATER_THAN;
            case GREATER_OR_EQUAL_THAN_VALUE:
                return FilterOperator.GREATER_OR_EQUAL_THAN;
            case LESS_THAN_VALUE:
                return FilterOperator.LESS_THAN;
            case LESS_OR_EQUAL_THAN_VALUE:
                return FilterOperator.LESS_OR_EQUAL_THAN;
            case IN_VALUE:
                return FilterOperator.IN;
            case CONTAINS_VALUE:
                return FilterOperator.CONTAINS;
            case I_CONTAINS_VALUE:
                return FilterOperator.I_CONTAINS;
            case NOT_CONTAINS_VALUE:
                return FilterOperator.NOT_CONTAINS;
            default:
                throw new IllegalEnumValueException(FilterOperator.class, value);
        }
    }

}
