package org.dalvarez.shop.shop_core.article.application.sum.by_price;

import org.dalvarez.shop.shop_core.article.domain.ArticleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleByPriceAdderConfig {

    @Bean
    public ArticleByPriceAdder articleByPriceAdder(ArticleRepository articleRepository) {
        return new ArticleByPriceAdder(articleRepository);
    }

}