package org.dalvarez.ddd_example.article.application.create;

import org.dalvarez.ddd_example.article.application.ArticleApplicationModuleTestCase;
import org.dalvarez.ddd_example.article.application.ArticleRequest;
import org.dalvarez.ddd_example.article.domain.ArticleMother;
import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.category.domain.repository.CategoryRepository;
import org.dalvarez.ddd_example.shared.domain.category.DomainCategoryByIdFinder;
import org.dalvarez.ddd_example.shared.domain.transaction_handler.TransactionHandler;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

final class ArticleCreatorShouldTestCase extends ArticleApplicationModuleTestCase {

    private final ArticleCreator articleCreator;

    private final TransactionHandler transactionHandler;

    ArticleCreatorShouldTestCase() {
        final CategoryRepository categoryRepository = mock(CategoryRepository.class);
        final DomainCategoryByIdFinder categoryByIdFinder = new DomainCategoryByIdFinder(categoryRepository);
        transactionHandler = mock(TransactionHandler.class);
        articleCreator = new ArticleCreator(repository, categoryByIdFinder, eventBus, transactionHandler);
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

        doNothing().when(transactionHandler).runInNewTransaction(any(Runnable.class));

        articleCreator.create(randomRequest);

        shouldHaveExecutedInNewTransaction();
    }

    private void shouldHaveExecutedInNewTransaction() {
        verify(transactionHandler, atLeastOnce()).runInNewTransaction(any(Runnable.class));
    }

}
