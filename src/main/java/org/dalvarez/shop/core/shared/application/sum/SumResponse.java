package org.dalvarez.shop.core.shared.application.sum;

public final class SumResponse<T extends Number> {

    private final T sum;

    public SumResponse(final T sum) {
        this.sum = sum;
    }

    public T getSum() {
        return sum;
    }

}
