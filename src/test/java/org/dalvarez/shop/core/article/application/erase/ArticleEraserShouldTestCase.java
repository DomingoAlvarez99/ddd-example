package org.dalvarez.shop.core.article.application.erase;

import org.dalvarez.shop.core.article.application.ArticleApplicationModuleTestCase;
import org.junit.jupiter.api.Test;

public final class ArticleEraserShouldTestCase extends ArticleApplicationModuleTestCase {

    private final ArticleEraser articleEraser;

    public ArticleEraserShouldTestCase() {
        this.articleEraser = new ArticleEraser(repository);
    }

    @Test
    public void eraseAnArticle() {
        final Long id = 0L;

        articleEraser.erase(id);

        shouldHaveErased(id);
    }

}