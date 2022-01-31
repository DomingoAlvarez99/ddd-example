package org.dalvarez.ddd_example.shared.domain.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AggregateRoot {

    private List<DomainEvent> domainEvents = new ArrayList<>();

    final public List<DomainEvent> pullDomainEvents() {
        final List<DomainEvent> events = domainEvents;

        domainEvents = Collections.emptyList();

        return events;
    }

    final protected void record(final DomainEvent event) {
        domainEvents.removeIf(localEvent -> localEvent.hasSameEventName(event));

        domainEvents.add(event);
    }

}
