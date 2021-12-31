package org.dalvarez.shop.shop_core.article.application.update;

import org.dalvarez.shop.shop_core.article.domain.port.ArticleRepository;
import org.dalvarez.shop.shop_core.category.domain.port.CategoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleUpdaterConfig {

    @Bean
    public ArticleUpdater articleUpdater(final ArticleRepository articleRepository,
                                         final
                                         CategoryRepository categoryRepository) {
        return new ArticleUpdater(articleRepository, categoryRepository);
    }

}

