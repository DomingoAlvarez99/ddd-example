package org.dalvarez.shop.core.article.application.find.by_id;

import org.dalvarez.shop.core.article.domain.ArticleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleByIdFinderConfig {

    @Bean
    public ArticleByIdFinder articleByIdFinder(ArticleRepository articleRepository) {
        return new ArticleByIdFinder(articleRepository);
    }

}
