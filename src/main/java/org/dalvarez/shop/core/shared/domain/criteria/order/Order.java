package org.dalvarez.shop.core.shared.domain.criteria.order;

public final class Order {

    private final String field;

    private final OrderType type;

    public Order() {
        field = null;
        this.type = OrderType.NONE;
    }

    public Order(final String field,
                 final OrderType type) {
        this.field = field;
        this.type = type;

        if (field == null && hasOrder())
            throw new IllegalArgumentException("Field must not be null");
    }

    public String getField() {
        return field;
    }

    public OrderType getType() {
        return type;
    }

    public boolean hasOrder() {
        return !type.isNone();
    }

    @Override
    public String toString() {
        return "Order{" +
                "field='" + field + '\'' +
                ", type=" + type +
                '}';
    }

}
