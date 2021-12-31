package org.dalvarez.shop.shop_core.article.application.find.by_id;

import org.dalvarez.shop.shop_core.shop_common.shared.domain.value_object.id.Identifier;
import org.dalvarez.shop.shop_core.article.application.ArticleApplicationModuleTestCase;
import org.dalvarez.shop.shop_core.article.domain.ArticleMother;
import org.dalvarez.shop.shop_core.article.domain.model.Article;
import org.dalvarez.shop.shop_core.article.domain.model.ArticleId;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;

public final class ArticleByIdFinderShouldTestCase extends ArticleApplicationModuleTestCase {

    private final ArticleByIdFinder articleByIdFinder;

    public ArticleByIdFinderShouldTestCase() {
        articleByIdFinder = new ArticleByIdFinder(repository);
    }

    @Test
    public void findAnArticleById() {
        final String id = Identifier.random().value();
        final ArticleId articleId = ArticleId.of(id);

        final Article article = ArticleMother.random(id);
        when(repository.getById(articleId)).thenReturn(article);

        articleByIdFinder.find(id);

        shouldHaveFoundById(articleId);
    }

}
