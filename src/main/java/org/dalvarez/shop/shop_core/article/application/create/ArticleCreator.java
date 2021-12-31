package org.dalvarez.shop.shop_core.article.application.create;

import org.dalvarez.shop.shop_common.shared.domain.bus.EventBus;
import org.dalvarez.shop.shop_core.article.application.ArticleRequest;
import org.dalvarez.shop.shop_core.article.application.ArticleResponse;
import org.dalvarez.shop.shop_core.article.domain.event.ArticleCreatedDomainEvent;
import org.dalvarez.shop.shop_core.article.domain.model.Article;
import org.dalvarez.shop.shop_core.article.domain.model.ArticleDescription;
import org.dalvarez.shop.shop_core.article.domain.model.ArticleId;
import org.dalvarez.shop.shop_core.article.domain.model.ArticleName;
import org.dalvarez.shop.shop_core.article.domain.model.ArticlePrice;
import org.dalvarez.shop.shop_core.article.domain.model.ArticleStock;
import org.dalvarez.shop.shop_core.article.domain.port.ArticleRepository;
import org.dalvarez.shop.shop_core.category.domain.port.CategoryRepository;
import org.dalvarez.shop.shop_core.shared.domain.category.CategoryId;

import java.util.Collections;
import java.util.Objects;

public final class ArticleCreator {

    private final ArticleRepository articleRepository;

    private final CategoryRepository categoryRepository;

    private final EventBus eventBus;

    public ArticleCreator(final ArticleRepository articleRepository,
                          final CategoryRepository categoryRepository,
                          final EventBus eventBus) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
        this.eventBus = eventBus;
    }

    public ArticleResponse create(final ArticleRequest request) {
        final ArticleId articleId = ArticleId.random();
        final ArticleName articleName = ArticleName.of(request.name());
        final ArticleDescription articleDescription = ArticleDescription.of(request.description());
        final ArticlePrice articlePrice = ArticlePrice.of(request.price());
        final ArticleStock articleStock = ArticleStock.of(request.stock());
        final CategoryId categoryId = request.id() == null ? null : CategoryId.of(request.id());

        final Article article = Article.of(
                articleId,
                articleStock,
                articlePrice,
                articleName,
                articleDescription,
                categoryId
        );

        if (Objects.nonNull(article.categoryId()))
            categoryRepository.getById(article.categoryId());

        final Article articleCreated = articleRepository.create(article);

        eventBus.publish(Collections.singletonList(new ArticleCreatedDomainEvent(articleCreated)));

        return ArticleResponse.fromArticle(articleCreated);
    }

}
