package org.dalvarez.shop.core.article.infrastructure.rest_api.controller.get.sum.by_price;

import org.dalvarez.shop.core.article.application.sum.by_price.ArticleByPriceAdder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleGetPriceSumByCriteriaControllerConfig {

    @Bean
    public ArticleGetPriceSumByCriteriaController articleGetPriceSumByCriteriaController(final ArticleByPriceAdder articleByPriceAdder) {
        return new ArticleGetPriceSumByCriteriaController(articleByPriceAdder);
    }

}
