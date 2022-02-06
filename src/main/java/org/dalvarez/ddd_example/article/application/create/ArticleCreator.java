package org.dalvarez.ddd_example.article.application.create;

import org.dalvarez.ddd_example.article.application.ArticleRequest;
import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.article.domain.model.ArticleDescription;
import org.dalvarez.ddd_example.article.domain.model.ArticleId;
import org.dalvarez.ddd_example.article.domain.model.ArticleName;
import org.dalvarez.ddd_example.article.domain.model.ArticlePrice;
import org.dalvarez.ddd_example.article.domain.model.ArticleStock;
import org.dalvarez.ddd_example.article.domain.repository.ArticleRepository;
import org.dalvarez.ddd_example.shared.domain.bus.EventBus;
import org.dalvarez.ddd_example.shared.domain.category.CategoryId;
import org.dalvarez.ddd_example.shared.domain.category.DomainCategoryByIdFinder;
import org.dalvarez.ddd_example.shared.domain.transaction_handler.TransactionHandler;

import java.util.Objects;

public final class ArticleCreator {

    private final ArticleRepository articleRepository;

    private final DomainCategoryByIdFinder categoryByIdFinder;

    private final EventBus eventBus;

    private final TransactionHandler transactionHandler;

    public ArticleCreator(final ArticleRepository articleRepository,
                          final DomainCategoryByIdFinder categoryByIdFinder,
                          final EventBus eventBus,
                          final TransactionHandler transactionHandler) {
        this.articleRepository = articleRepository;
        this.categoryByIdFinder = categoryByIdFinder;
        this.eventBus = eventBus;
        this.transactionHandler = transactionHandler;
    }

    public void create(final ArticleRequest request) {
        final ArticleId articleId = ArticleId.random();
        final ArticleName articleName = ArticleName.of(request.name());
        final ArticleDescription articleDescription = ArticleDescription.of(request.description());
        final ArticlePrice articlePrice = ArticlePrice.of(request.price());
        final ArticleStock articleStock = ArticleStock.of(request.stock());
        final CategoryId categoryId = request.id() == null ? null : CategoryId.of(request.id());

        final Article article = Article.create(
                articleId,
                articleStock,
                articlePrice,
                articleName,
                articleDescription,
                categoryId
        );

        if (Objects.nonNull(article.categoryId()))
            categoryByIdFinder.find(article.categoryId());

        transactionHandler.runInNewTransaction(() -> {
            articleRepository.createOrUpdate(article);

            eventBus.publish(article.pullDomainEvents());
        });
    }

}
