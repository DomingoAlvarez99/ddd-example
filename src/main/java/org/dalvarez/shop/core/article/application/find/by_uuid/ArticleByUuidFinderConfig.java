package org.dalvarez.shop.core.article.application.find.by_uuid;

import org.dalvarez.shop.core.article.domain.ArticleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleByUuidFinderConfig {

    @Bean
    public ArticleByUuidFinder articleByUuidFinder(ArticleRepository articleRepository) {
        return new ArticleByUuidFinder(articleRepository);
    }

}
