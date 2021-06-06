package org.dalvarez.shop.core.article.infrastructure.rest_api.controller.post;

import org.dalvarez.shop.core.article.application.create.ArticleCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticlePostControllerConfig {

    @Bean
    public ArticlePostController articlePostController(final ArticleCreator articleCreator) {
        return new ArticlePostController(articleCreator);
    }

}
