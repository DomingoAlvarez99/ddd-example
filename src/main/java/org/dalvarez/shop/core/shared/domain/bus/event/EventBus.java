package org.dalvarez.shop.core.shared.domain.bus.event;

import java.util.List;

public interface EventBus {

    void publish(final List<DomainEvent<?>> events);

}
