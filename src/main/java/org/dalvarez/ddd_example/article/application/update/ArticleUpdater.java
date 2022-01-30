package org.dalvarez.ddd_example.article.application.update;

import org.dalvarez.ddd_example.article.application.ArticleRequest;
import org.dalvarez.ddd_example.article.application.ArticleResponse;
import org.dalvarez.ddd_example.article.domain.event.ArticleUpdatedDomainEvent;
import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.article.domain.model.ArticleId;
import org.dalvarez.ddd_example.article.domain.repository.ArticleRepository;
import org.dalvarez.ddd_example.shared.domain.bus.EventBus;
import org.dalvarez.ddd_example.shared.domain.category.CategoryId;
import org.dalvarez.ddd_example.shared.domain.category.DomainCategoryByIdFinder;

import java.util.Collections;
import java.util.Objects;

public final class ArticleUpdater {

    private final ArticleRepository articleRepository;

    private final DomainCategoryByIdFinder categoryByIdFinder;

    private final EventBus eventBus;

    public ArticleUpdater(final ArticleRepository articleRepository,
                          final DomainCategoryByIdFinder categoryByIdFinder,
                          final EventBus eventBus) {
        this.articleRepository = articleRepository;
        this.categoryByIdFinder = categoryByIdFinder;
        this.eventBus = eventBus;
    }

    public ArticleResponse update(final String id,
                                  final ArticleRequest request) {
        final ArticleId articleId = ArticleId.of(id);
        final Article article = articleRepository.getById(articleId);

        final CategoryId categoryId = request.id() == null ? null : CategoryId.of(request.id());

        if (Objects.nonNull(categoryId))
            categoryByIdFinder.find(categoryId);

        article.updateCategory(categoryId);

        Article articleUpdated = articleRepository.update(article);

        eventBus.publish(Collections.singletonList(new ArticleUpdatedDomainEvent(articleUpdated)));

        return ArticleResponse.fromArticle(articleUpdated);
    }

}
