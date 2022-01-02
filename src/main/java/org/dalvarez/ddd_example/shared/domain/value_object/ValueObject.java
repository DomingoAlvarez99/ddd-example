package org.dalvarez.ddd_example.shared.domain.value_object;

import java.util.Objects;

public abstract class ValueObject<T> {

    private final T value;

    protected ValueObject(final T value) {
        this.value = value;
    }

    public final T value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ValueObject))
            return false;

        ValueObject<T> that = (ValueObject<T>) o;

        return Objects.equals(value, that.value);
    }

    public final boolean isNotEquals(Object o) {
        return !equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
