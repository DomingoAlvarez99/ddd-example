package org.dalvarez.shop.core.article.application.find.by_id;

import org.dalvarez.shop.core.article.application.ArticleApplicationModuleTestCase;
import org.dalvarez.shop.core.article.domain.ArticleMother;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;

public final class ArticleByIdFinderShouldTestCase extends ArticleApplicationModuleTestCase {

    private final ArticleByIdFinder articleByIdFinder;

    public ArticleByIdFinderShouldTestCase() {
        articleByIdFinder = new ArticleByIdFinder(repository);
    }

    @Test
    public void findAnArticleById() {
        final Long id = 1L;

        when(repository.getById(id)).thenReturn(ArticleMother.random());

        articleByIdFinder.find(id);

        shouldHaveFoundById(id);
    }

}
