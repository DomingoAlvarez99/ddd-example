package org.dalvarez.shop.shop_core.article.infrastructure.rest_api.controller.delete;

import org.dalvarez.shop.shop_core.article.application.erase.ArticleEraser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleDeleteControllerConfig {

    @Bean
    public ArticleDeleteController articleDeleteController(final ArticleEraser articleEraser) {
        return new ArticleDeleteController(articleEraser);
    }

}
