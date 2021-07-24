package org.dalvarez.shop.shop_core.article.infrastructure.rest_api.controller.get.by_criteria;

import org.dalvarez.shop.shop_core.article.application.find.by_criteria.ArticleByCriteriaFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleGetByCriteriaControllerConfig {

    @Bean
    public ArticleGetByCriteriaController articleGetByCriteriaController(final ArticleByCriteriaFinder articleByCriteriaFinder) {
        return new ArticleGetByCriteriaController(articleByCriteriaFinder);
    }

}
