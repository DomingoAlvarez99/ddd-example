package org.dalvarez.shop.core.article.application.create;

import org.dalvarez.shop.core.article.domain.ArticleRepository;
import org.dalvarez.shop.core.shared.domain.bus.event.EventBus;
import org.dalvarez.shop.core.shared.domain.generator.UuidGenerator;
import org.dalvarez.shop.core.shared.infrastructure.bus.event.SpringApplicationEventBusConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleCreatorConfig {

    @Bean
    public ArticleCreator articleCreator(final ArticleRepository articleRepository,
                                         final UuidGenerator uuidGenerator,
                                         @Qualifier(SpringApplicationEventBusConfig.BEAN_NAME) EventBus eventBus) {
        return new ArticleCreator(
                articleRepository,
                uuidGenerator,
                eventBus
        );
    }

}

