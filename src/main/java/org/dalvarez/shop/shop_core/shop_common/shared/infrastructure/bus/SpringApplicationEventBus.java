package org.dalvarez.shop.shop_core.shop_common.shared.infrastructure.bus;

import org.dalvarez.shop.shop_core.shop_common.shared.domain.bus.DomainEvent;
import org.dalvarez.shop.shop_core.shop_common.shared.domain.bus.EventBus;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;

public final class SpringApplicationEventBus implements EventBus {

    private final ApplicationEventPublisher publisher;

    public SpringApplicationEventBus(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(final List<DomainEvent<?>> events) {
        events.forEach(this::publish);
    }

    private void publish(final DomainEvent<?> event) {
        this.publisher.publishEvent(event);
    }

}
