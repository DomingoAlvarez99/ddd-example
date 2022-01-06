package org.dalvarez.ddd_example.article.application;

import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.article.domain.repository.ArticleRepository;
import org.dalvarez.ddd_example.shared.application.UnitTestCase;
import org.dalvarez.ddd_example.shared.domain.criteria.Criteria;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public abstract class ArticleApplicationModuleTestCase extends UnitTestCase<Article, ArticleRepository> {

    protected ArticleApplicationModuleTestCase() {
        super(ArticleRepository.class);
    }

    protected void shouldHaveAddedByPrice(final Criteria criteria) {
        verify(repository, atLeastOnce()).sumPriceByCriteria(criteria);
    }

    protected void shouldHaveAddedByStock(final Criteria criteria) {
        verify(repository, atLeastOnce()).sumStockByCriteria(criteria);
    }

}
