package org.dalvarez.shop.core.article.application.sum.by_stock;

import org.dalvarez.shop.core.article.application.ArticleApplicationModuleTestCase;
import org.dalvarez.shop.core.shared.domain.criteria.Criteria;
import org.junit.jupiter.api.Test;

public final class ArticleByStockAdderShouldTestCase extends ArticleApplicationModuleTestCase {

    private final ArticleByStockAdder articleByStockAdder;

    public ArticleByStockAdderShouldTestCase() {
        articleByStockAdder = new ArticleByStockAdder(repository);
    }

    @Test
    public void sumAnArticle() {
        final Criteria criteria = new Criteria();

        articleByStockAdder.sum(criteria);

        shouldHaveAddedByStock(criteria);
    }

}
