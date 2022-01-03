package org.dalvarez.ddd_example.article.application.erase;

import org.dalvarez.ddd_example.article.application.ArticleApplicationModuleTestCase;
import org.dalvarez.ddd_example.article.domain.model.ArticleId;
import org.dalvarez.ddd_example.shared.domain.IdMother;
import org.junit.jupiter.api.Test;

final class ArticleEraserShouldTestCase extends ArticleApplicationModuleTestCase {

    private final ArticleEraser articleEraser;

    ArticleEraserShouldTestCase() {
        this.articleEraser = new ArticleEraser(repository);
    }

    @Test
    void eraseAnArticle() {
        final String id = IdMother.randomPick();

        articleEraser.erase(id);

        shouldHaveErased(ArticleId.of(id));
    }

}