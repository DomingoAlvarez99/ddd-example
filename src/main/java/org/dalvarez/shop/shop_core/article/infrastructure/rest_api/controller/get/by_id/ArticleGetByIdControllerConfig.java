package org.dalvarez.shop.shop_core.article.infrastructure.rest_api.controller.get.by_id;

import org.dalvarez.shop.shop_core.article.application.find.by_id.ArticleByIdFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleGetByIdControllerConfig {

    @Bean
    public ArticleGetByIdController articleGetByIdController(final ArticleByIdFinder articleByIdFinder) {
        return new ArticleGetByIdController(articleByIdFinder);
    }

}
