package org.dalvarez.shop.shop_core.article.application.find.by_criteria;

import org.dalvarez.shop.shop_core.article.domain.ArticleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleByCriteriaFinderConfig {

    @Bean
    public ArticleByCriteriaFinder articleByCriteriaFinder(ArticleRepository articleRepository) {
        return new ArticleByCriteriaFinder(articleRepository);
    }

}
