package org.dalvarez.shop.shop_core.article.application.create;

import org.dalvarez.shop.shop_common.shared.domain.log.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OnArticleCreatedEventConfig {

    @Bean
    public OnArticleCreatedEvent onArticleCreatedEvent(final Logger log) {
        return new OnArticleCreatedEvent(log);
    }

}

