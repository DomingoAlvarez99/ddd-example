package org.dalvarez.shop.core.article.infrastructure.rest_api.controller.delete;

import org.dalvarez.shop.core.article.application.erase.ArticleEraser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleDeleteControllerConfig {

    @Bean
    public ArticleDeleteController articleDeleteController(final ArticleEraser articleEraser) {
        return new ArticleDeleteController(articleEraser);
    }

}
