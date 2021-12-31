package org.dalvarez.shop.shop_core.article.application.create;

import org.dalvarez.shop.shop_common.shared.domain.bus.EventBus;
import org.dalvarez.shop.shop_common.shared.infrastructure.bus.SpringApplicationEventBusConfig;
import org.dalvarez.shop.shop_core.article.domain.port.ArticleRepository;
import org.dalvarez.shop.shop_core.category.domain.port.CategoryRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleCreatorConfig {

    @Bean
    public ArticleCreator articleCreator(final ArticleRepository articleRepository,
                                         final CategoryRepository categoryRepository,
                                         @Qualifier(SpringApplicationEventBusConfig.BEAN_NAME) EventBus eventBus) {
        return new ArticleCreator(
                articleRepository,
                categoryRepository,
                eventBus
        );
    }

}

