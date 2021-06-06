package org.dalvarez.shop.core.article.infrastructure.rest_api.controller.get.sum.by_stock;

import org.dalvarez.shop.core.article.application.sum.by_stock.ArticleByStockAdder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleGetStockSumByCriteriaControllerConfig {

    @Bean
    public ArticleGetStockSumByCriteriaController articleGetStockSumByCriteriaController(final ArticleByStockAdder articleByStockAdder) {
        return new ArticleGetStockSumByCriteriaController(articleByStockAdder);
    }

}
