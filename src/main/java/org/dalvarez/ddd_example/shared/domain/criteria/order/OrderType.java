package org.dalvarez.ddd_example.shared.domain.criteria.order;

import org.dalvarez.ddd_example.shared.domain.exception.IllegalEnumValueException;

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

    public String type() {
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
                        .orElse("")
                        .toUpperCase()) {
            case ASC_VALUE:
                return OrderType.ASC;
            case DESC_VALUE:
                return OrderType.DESC;
            case NONE_VALUE:
            case "":
                return NONE;
            default:
                throw new IllegalEnumValueException(OrderType.class, value);
        }
    }

}
