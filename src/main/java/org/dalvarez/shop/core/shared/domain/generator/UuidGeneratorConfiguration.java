package org.dalvarez.shop.core.shared.domain.generator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UuidGeneratorConfiguration {

    @Bean
    public UuidGenerator uuidGenerator() {
        return new DefaultUuidGenerator();
    }

}