package org.dalvarez.ddd_example.article.application.sum.by_stock;

import org.dalvarez.ddd_example.article.application.ArticleApplicationModuleTestCase;
import org.dalvarez.ddd_example.shared.domain.criteria.Criteria;
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