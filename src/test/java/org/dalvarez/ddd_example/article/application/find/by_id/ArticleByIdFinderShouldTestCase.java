package org.dalvarez.ddd_example.article.application.find.by_id;

import org.dalvarez.ddd_example.article.application.ArticleApplicationModuleTestCase;
import org.dalvarez.ddd_example.article.domain.ArticleMother;
import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.article.domain.model.ArticleId;
import org.dalvarez.ddd_example.shared.domain.value_object.id.Identifier;
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
