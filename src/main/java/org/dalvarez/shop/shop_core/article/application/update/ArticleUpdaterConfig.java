package org.dalvarez.shop.shop_core.article.application.update;

import org.dalvarez.shop.shop_core.article.domain.ArticleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleUpdaterConfig {

    @Bean
    public ArticleUpdater articleUpdater(final ArticleRepository articleRepository) {
        return new ArticleUpdater(articleRepository);
    }

}

