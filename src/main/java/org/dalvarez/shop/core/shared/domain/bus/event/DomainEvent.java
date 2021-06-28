package org.dalvarez.shop.core.shared.domain.bus.event;

import java.time.Instant;
import java.util.Objects;

public abstract class DomainEvent<T> {

    private final Instant date;

    private final T value;

    public DomainEvent() {
        date = Instant.now();
        value = null;
    }

    protected DomainEvent(final T value) {
        this.date = Instant.now();
        this.value = value;
    }

    public abstract String eventName();

    public Instant getDate() {
        return date;
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        final DomainEvent<?> that = (DomainEvent<?>) o;

        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }


}
