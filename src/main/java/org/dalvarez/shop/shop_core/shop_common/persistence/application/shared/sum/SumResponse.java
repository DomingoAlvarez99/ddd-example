package org.dalvarez.shop.shop_core.shop_common.persistence.application.shared.sum;

public final class SumResponse<T extends Number> {

    private final T sum;

    public SumResponse(final T sum) {
        this.sum = sum;
    }

    public T getSum() {
        return sum;
    }

}
