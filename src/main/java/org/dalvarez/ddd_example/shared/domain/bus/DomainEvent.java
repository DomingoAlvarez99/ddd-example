package org.dalvarez.ddd_example.shared.domain.bus;

import org.dalvarez.ddd_example.shared.domain.value_object.id.Identifier;

import java.time.Instant;
import java.util.Objects;

public abstract class DomainEvent {

    private final Instant date;

    private final Identifier id;

    private final Identifier aggregateId;

    private DomainEvent() {
        this.aggregateId = null;
        date = null;
        id = null;
    }

    protected DomainEvent(final Identifier aggregateId) {
        this.aggregateId = aggregateId;
        this.date = Instant.now();
        this.id = Identifier.random();
    }

    public abstract String eventName();

    public Instant date() {
        return date;
    }

    public Identifier id() {
        return id;
    }

    public Identifier aggregateId() {
        return id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainEvent that = (DomainEvent) o;
        return Objects.equals(date, that.date) && Objects.equals(id, that.id) && Objects.equals(
                aggregateId,
                that.aggregateId
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, id, aggregateId);
    }

    @Override
    public String toString() {
        return "DomainEvent{" +
                "date=" + date +
                ", id=" + id +
                ", aggregateId=" + aggregateId +
                '}';
    }

}
