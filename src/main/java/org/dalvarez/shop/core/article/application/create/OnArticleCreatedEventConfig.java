package org.dalvarez.shop.core.article.application.create;

import org.dalvarez.shop.shared.log.domain.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OnArticleCreatedEventConfig {

    @Bean
    public OnArticleCreatedEvent onArticleCreatedEvent(final Logger log) {
        return new OnArticleCreatedEvent(log);
    }

}

