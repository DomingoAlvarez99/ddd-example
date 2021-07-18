package org.dalvarez.shop.core.article.application.count.by_criteria;

import org.dalvarez.shop.core.article.domain.ArticleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleByCriteriaCounterConfig {

    @Bean
    public ArticleByCriteriaCounter articleByCriteriaCounter(final ArticleRepository articleRepository) {
        return new ArticleByCriteriaCounter(articleRepository);
    }

}

