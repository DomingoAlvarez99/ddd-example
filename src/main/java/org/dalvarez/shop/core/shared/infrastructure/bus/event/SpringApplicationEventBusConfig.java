package org.dalvarez.shop.core.shared.infrastructure.bus.event;

import org.dalvarez.shop.core.shared.domain.bus.event.EventBus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringApplicationEventBusConfig {

    public static final String BEAN_NAME = "SpringApplicationEventBusConfig";

    @Bean
    @Qualifier(BEAN_NAME)
    public EventBus springApplicationEventBus(ApplicationEventPublisher publisher) {
        return new SpringApplicationEventBus(publisher);
    }

}
