package org.dalvarez.shop.shop_core.article.application.erase;

import org.dalvarez.shop.shop_core.article.application.ArticleApplicationModuleTestCase;
import org.dalvarez.shop.shop_core.article.domain.model.ArticleId;
import org.dalvarez.shop.shop_core.shared.domain.IdMother;
import org.junit.jupiter.api.Test;

public final class ArticleEraserShouldTestCase extends ArticleApplicationModuleTestCase {

    private final ArticleEraser articleEraser;

    public ArticleEraserShouldTestCase() {
        this.articleEraser = new ArticleEraser(repository);
    }

    @Test
    public void eraseAnArticle() {
        final String id = IdMother.randomPick();

        articleEraser.erase(id);

        shouldHaveErased(ArticleId.of(id));
    }

}