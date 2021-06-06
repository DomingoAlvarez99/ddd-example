package org.dalvarez.shop.core.article.application.sum.by_stock;

import org.dalvarez.shop.core.article.domain.ArticleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleByStockAdderConfig {

    @Bean
    public ArticleByStockAdder articleByStockAdder(ArticleRepository articleRepository) {
        return new ArticleByStockAdder(articleRepository);
    }

}