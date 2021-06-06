package org.dalvarez.shop.core.shared.domain.criteria.order;

import org.dalvarez.shop.core.shared.domain.exception.IllegalEnumValueException;
import org.dalvarez.shop.core.shared.domain.util.StringUtils;

import java.util.Optional;

public enum OrderType {

    ASC(OrderType.ASC_VALUE),
    DESC(OrderType.DESC_VALUE),
    NONE(OrderType.NONE_VALUE);

    private static final String ASC_VALUE = "ASC";

    private static final String DESC_VALUE = "DESC";

    private static final String NONE_VALUE = "NONE";

    private final String type;

    OrderType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public boolean isAsc() {
        return this.equals(ASC);
    }

    public boolean isDesc() {
        return this.equals(DESC);
    }

    public boolean isNone() {
        return this.equals(NONE);
    }

    public static OrderType fromValue(final String value) {
        switch (Optional.ofNullable(value)
                        .orElse(StringUtils.EMPTY)
                        .toUpperCase()) {
            case ASC_VALUE:
                return OrderType.ASC;
            case DESC_VALUE:
                return OrderType.DESC;
            case NONE_VALUE:
            case StringUtils.EMPTY:
                return NONE;
            default:
                throw new IllegalEnumValueException(OrderType.class, value);
        }
    }

}
