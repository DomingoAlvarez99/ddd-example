package org.dalvarez.ddd_example.article.application.update;

import org.dalvarez.ddd_example.article.application.ArticleApplicationModuleTestCase;
import org.dalvarez.ddd_example.article.application.ArticleRequest;
import org.dalvarez.ddd_example.article.domain.ArticleMother;
import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.category.domain.repository.CategoryRepository;
import org.dalvarez.ddd_example.shared.domain.category.DomainCategoryByIdFinder;
import org.dalvarez.ddd_example.shared.domain.value_object.id.Identifier;
import org.dalvarez.ddd_example.shared.infrastructure.shared.TestConfig;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestConfig
final class ArticleUpdaterShouldTestCase extends ArticleApplicationModuleTestCase {

    private final ArticleUpdater articleUpdater;

    ArticleUpdaterShouldTestCase() {
        final CategoryRepository categoryRepository = mock(CategoryRepository.class);
        final DomainCategoryByIdFinder categoryByIdFinder = new DomainCategoryByIdFinder(categoryRepository);
        articleUpdater = new ArticleUpdater(repository, categoryByIdFinder, eventBus);
    }

    @Test
    void updateAnArticle() {
        final String id = Identifier.random().value();
        final Article random = ArticleMother.random(id);

        final ArticleRequest randomRequest = ArticleRequest.of(
                random.id().value(),
                random.stock().value(),
                random.price().value(),
                random.name().value(),
                random.description().value(),
                null
        );

        when(repository.getById(any())).thenReturn(random);

        when(repository.update(any())).thenReturn(random);

        articleUpdater.update(id, randomRequest);

        shouldHaveUpdated(random);
    }

}
