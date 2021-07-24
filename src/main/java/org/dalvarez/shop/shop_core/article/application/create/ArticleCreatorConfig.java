package org.dalvarez.shop.shop_core.article.application.create;

import org.dalvarez.shop.shop_core.article.domain.ArticleRepository;
import org.dalvarez.shop.shop_shared.persistence.domain.uuid_generator.UuidGenerator;
import org.dalvarez.shop.shop_shared.event.domain.EventBus;
import org.dalvarez.shop.shop_shared.event.infrastructure.SpringApplicationEventBusConfig;
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

