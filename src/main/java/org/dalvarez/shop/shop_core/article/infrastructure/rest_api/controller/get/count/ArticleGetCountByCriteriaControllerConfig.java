package org.dalvarez.shop.shop_core.article.infrastructure.rest_api.controller.get.count;

import org.dalvarez.shop.shop_core.article.application.count.by_criteria.ArticleByCriteriaCounter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleGetCountByCriteriaControllerConfig {

    @Bean
    public ArticleGetCountByCriteriaController articleGetCountByCriteriaController(final ArticleByCriteriaCounter articleByCriteriaCounter) {
        return new ArticleGetCountByCriteriaController(articleByCriteriaCounter);
    }

}
