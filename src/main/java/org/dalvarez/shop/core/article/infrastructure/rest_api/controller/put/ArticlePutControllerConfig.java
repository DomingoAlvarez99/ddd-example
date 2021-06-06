package org.dalvarez.shop.core.article.infrastructure.rest_api.controller.put;

import org.dalvarez.shop.core.article.application.update.ArticleUpdater;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticlePutControllerConfig {

    @Bean
    public ArticlePutController articlePutController(final ArticleUpdater articleUpdater) {
        return new ArticlePutController(articleUpdater);
    }

}
