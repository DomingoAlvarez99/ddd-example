package org.dalvarez.shop.shop_core.shop_common.shared.infrastructure.bus;


import org.dalvarez.shop.shop_core.shop_common.shared.domain.bus.EventBus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringApplicationEventBusConfig {

    public static final String BEAN_NAME = "spring_application-event_bus";

    @Bean
    @Qualifier(BEAN_NAME)
    public EventBus springApplicationEventBus(ApplicationEventPublisher publisher) {
        return new SpringApplicationEventBus(publisher);
    }

}
