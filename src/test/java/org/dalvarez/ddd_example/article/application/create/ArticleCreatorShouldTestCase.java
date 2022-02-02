package org.dalvarez.ddd_example.article.application.create;

import org.dalvarez.ddd_example.article.application.ArticleApplicationModuleTestCase;
import org.dalvarez.ddd_example.article.application.ArticleRequest;
import org.dalvarez.ddd_example.article.domain.ArticleMother;
import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.category.domain.repository.CategoryRepository;
import org.dalvarez.ddd_example.shared.domain.bus.DomainEvent;
import org.dalvarez.ddd_example.shared.domain.category.DomainCategoryByIdFinder;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

final class ArticleCreatorShouldTestCase extends ArticleApplicationModuleTestCase {

    private final ArticleCreator articleCreator;

    ArticleCreatorShouldTestCase() {
        final CategoryRepository categoryRepository = mock(CategoryRepository.class);
        final DomainCategoryByIdFinder categoryByIdFinder = new DomainCategoryByIdFinder(categoryRepository);
        articleCreator = new ArticleCreator(repository, categoryByIdFinder, eventBus);
    }

    @Test
    void createAValidArticle() {
        final Article random = ArticleMother.random();

        final ArticleRequest randomRequest = ArticleRequest.of(
                random.stock().value(),
                random.price().value(),
                random.name().value(),
                random.description().value(),
                null
        );

        when(repository.getById(any())).thenReturn(Optional.of(random));

        doNothing().when(repository).createOrUpdate(any());

        final List<DomainEvent> domainEvents = random.pullDomainEvents();

        doNothing().when(eventBus).publish(domainEvents);

        articleCreator.create(randomRequest);

        shouldHaveCreated(random);

        shouldHavePublishedDomainEvents(domainEvents);
    }

}
