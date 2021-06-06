package org.dalvarez.shop.core.article.application.create;

import org.dalvarez.shop.core.shared.domain.log.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OnArticleCreatedEventConfig {

    @Bean
    public OnArticleCreatedEvent onArticleCreatedEvent(final Logger log) {
        return new OnArticleCreatedEvent(log);
    }

}

