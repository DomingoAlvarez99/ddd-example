package org.dalvarez.shop.shop_core.article.application.update;

import org.dalvarez.shop.shop_core.article.application.ArticleRequest;
import org.dalvarez.shop.shop_core.article.application.ArticleResponse;
import org.dalvarez.shop.shop_core.article.domain.model.Article;
import org.dalvarez.shop.shop_core.article.domain.model.ArticleId;
import org.dalvarez.shop.shop_core.article.domain.port.ArticleRepository;
import org.dalvarez.shop.shop_core.category.domain.port.CategoryRepository;
import org.dalvarez.shop.shop_core.shared.domain.category.CategoryId;

import java.util.Objects;

public class ArticleUpdater {

    private final ArticleRepository articleRepository;

    private final CategoryRepository categoryRepository;

    public ArticleUpdater(final ArticleRepository articleRepository,
                          final CategoryRepository categoryRepository) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
    }

    public ArticleResponse update(final String id,
                                  final ArticleRequest request) {
        final ArticleId articleId = ArticleId.of(id);
        final Article article = articleRepository.getById(articleId);

        final CategoryId categoryId = request.id() == null ? null : CategoryId.of(request.id());

        if (Objects.nonNull(categoryId))
            categoryRepository.getById(categoryId);

        article.updateCategory(categoryId);

        return ArticleResponse.fromArticle(articleRepository.update(article));
    }

}
