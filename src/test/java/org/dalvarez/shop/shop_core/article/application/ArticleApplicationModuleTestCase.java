package org.dalvarez.shop.shop_core.article.application;

import org.dalvarez.shop.shop_core.article.domain.Article;
import org.dalvarez.shop.shop_core.article.domain.ArticleRepository;
import org.dalvarez.shop.shop_core.shared.application.ApplicationModuleTestCase;
import org.dalvarez.shop.shop_common.persistence.domain.criteria.Criteria;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public abstract class ArticleApplicationModuleTestCase extends ApplicationModuleTestCase<Article, ArticleRepository> {

    public ArticleApplicationModuleTestCase() {
        super(ArticleRepository.class);
    }

    protected void shouldHaveAddedByPrice(final Criteria criteria) {
        verify(repository, atLeastOnce()).sumPriceByCriteria(criteria);
    }

    protected void shouldHaveAddedByStock(final Criteria criteria) {
        verify(repository, atLeastOnce()).sumStockByCriteria(criteria);
    }

}
