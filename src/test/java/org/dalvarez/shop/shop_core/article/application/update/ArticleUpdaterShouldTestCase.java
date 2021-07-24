package org.dalvarez.shop.shop_core.article.application.update;

import org.dalvarez.shop.shop_core.article.application.ArticleApplicationModuleTestCase;
import org.dalvarez.shop.shop_core.article.domain.Article;
import org.dalvarez.shop.shop_core.article.domain.ArticleMother;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;

public final class ArticleUpdaterShouldTestCase extends ArticleApplicationModuleTestCase {

    private final ArticleUpdater articleUpdater;

    public ArticleUpdaterShouldTestCase() {
        articleUpdater = new ArticleUpdater(repository);
    }

    @Test
    public void updateAnArticle() {
        final Article article = ArticleMother.random();

        when(repository.update(article)).thenReturn(article);

        articleUpdater.update(article);

        shouldHaveUpdated(article);
    }

}
