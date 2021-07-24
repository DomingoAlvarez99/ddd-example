package org.dalvarez.shop.shop_core.article.application.find.by_criteria;

import org.dalvarez.shop.shop_core.article.application.ArticleApplicationModuleTestCase;
import org.dalvarez.shop.shop_core.article.domain.ArticleMother;
import org.dalvarez.shop.shop_core.shared.domain.QueryResultMother;
import org.dalvarez.shop.shop_shared.persistence.domain.criteria.Criteria;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;

public final class ArticleByCriteriaFinderShouldTestCase extends ArticleApplicationModuleTestCase {

    private final ArticleByCriteriaFinder articleByCriteriaFinder;

    public ArticleByCriteriaFinderShouldTestCase() {
        this.articleByCriteriaFinder = new ArticleByCriteriaFinder(repository);
    }

    @Test
    public void findArticlesByCriteria() {
        final Criteria criteria = new Criteria();

        when(repository.getByCriteria(criteria)).thenReturn(QueryResultMother.fromList(ArticleMother.randomList()));

        articleByCriteriaFinder.find(criteria);

        shouldHaveFoundByCriteria(criteria);
    }

}
