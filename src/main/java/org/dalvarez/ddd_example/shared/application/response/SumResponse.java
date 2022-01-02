package org.dalvarez.ddd_example.shared.application.response;

public final class SumResponse<T extends Number> {

    private final T value;

    private SumResponse() {
        value = null;
    }

    public SumResponse(final T value) {
        this.value = value;
    }

    public T value() {
        return value;
    }

}
