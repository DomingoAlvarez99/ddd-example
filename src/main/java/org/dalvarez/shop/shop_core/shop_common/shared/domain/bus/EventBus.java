package org.dalvarez.shop.shop_core.shop_common.shared.domain.bus;

import java.util.List;

public interface EventBus {

    void publish(final List<DomainEvent<?>> events);

}
