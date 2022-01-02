package org.dalvarez.ddd_example.shared.domain.bus;

import java.util.List;

public interface EventBus {

    void publish(final List<DomainEvent> events);

}
