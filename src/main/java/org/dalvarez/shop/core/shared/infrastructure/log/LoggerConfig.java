package org.dalvarez.shop.core.shared.infrastructure.log;

import org.dalvarez.shop.core.shared.domain.log.Logger;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class LoggerConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Logger logger(final InjectionPoint ip) {
        return new Slf4jLogger(ip);
    }

}
