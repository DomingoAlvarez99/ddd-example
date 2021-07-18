package org.dalvarez.shop.core.article.infrastructure.hibernate_persistence;

import org.dalvarez.shop.core.article.domain.ArticleRepository;
import org.dalvarez.shop.shared.persistence.domain.criteria.CriteriaConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class ArticleRepositoryConfig {

    @Bean
    public ArticleRepository articleRepository(final EntityManager entityManager,
                                               final CriteriaConverter<ArticleEntity> hibernateCriteriaConverter) {
        return new HibernateArticleRepository(entityManager, hibernateCriteriaConverter);
    }

}

