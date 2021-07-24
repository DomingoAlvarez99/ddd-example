package org.dalvarez.shop.shop_core.article.infrastructure.rest_api.controller.get.by_uuid;

import org.dalvarez.shop.shop_core.article.application.find.by_uuid.ArticleByUuidFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleGetByUuidControllerConfig {

    @Bean
    public ArticleGetByUuidController articleGetByUuidController(final ArticleByUuidFinder articleByUuidFinder) {
        return new ArticleGetByUuidController(articleByUuidFinder);
    }

}
