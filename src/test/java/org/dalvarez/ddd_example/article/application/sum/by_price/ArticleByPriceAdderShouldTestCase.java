package org.dalvarez.ddd_example.article.application.sum.by_price;

import org.dalvarez.ddd_example.article.application.ArticleApplicationModuleTestCase;
import org.dalvarez.ddd_example.shared.domain.criteria.Criteria;
import org.junit.jupiter.api.Test;

public final class ArticleByPriceAdderShouldTestCase extends ArticleApplicationModuleTestCase {

    private final ArticleByPriceAdder articleByPriceAdder;

    public ArticleByPriceAdderShouldTestCase() {
        articleByPriceAdder = new ArticleByPriceAdder(repository);
    }

    @Test
    public void sumAnArticle() {
        final Criteria criteria = new Criteria();

        articleByPriceAdder.sum(criteria);

        shouldHaveAddedByPrice(criteria);
    }

}
