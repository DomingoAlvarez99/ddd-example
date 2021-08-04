package org.dalvarez.shop.shop_core.article.application.find.by_uuid;

import org.dalvarez.shop.shop_core.article.application.ArticleApplicationModuleTestCase;
import org.dalvarez.shop.shop_core.article.domain.Article;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;

public final class ArticleByUuidFinderShouldTestCase extends ArticleApplicationModuleTestCase {

    private final ArticleByUuidFinder articleByUuidFinder;

    public ArticleByUuidFinderShouldTestCase() {
        articleByUuidFinder = new ArticleByUuidFinder(repository);
    }

    @Test
    public void findAnArticleByUuid() {
        final String uuid = uuidGenerator.generate();

        final Article article = Article.of(
                null,
                null,
                50,
                22.22,
                "Art 1",
                "Desc 1"
        );
        when(repository.getByUuid(uuid)).thenReturn(article);

        articleByUuidFinder.find(uuid);

        shouldHaveFoundByUuid(uuid);
    }

}
