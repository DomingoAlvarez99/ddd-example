package org.dalvarez.ddd_example.article.application.update;

import org.dalvarez.ddd_example.article.application.ArticleRequest;
import org.dalvarez.ddd_example.article.application.ArticleResponse;
import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.article.domain.model.ArticleId;
import org.dalvarez.ddd_example.article.domain.repository.ArticleRepository;
import org.dalvarez.ddd_example.shared.domain.category.CategoryId;
import org.dalvarez.ddd_example.shared.domain.category.DomainCategoryByIdFinder;

import java.util.Objects;

public final class ArticleUpdater {

    private final ArticleRepository articleRepository;

    private final DomainCategoryByIdFinder categoryByIdFinder;

    public ArticleUpdater(final ArticleRepository articleRepository,
                          final DomainCategoryByIdFinder categoryByIdFinder) {
        this.articleRepository = articleRepository;
        this.categoryByIdFinder = categoryByIdFinder;
    }

    public ArticleResponse update(final String id,
                                  final ArticleRequest request) {
        final ArticleId articleId = ArticleId.of(id);
        final Article article = articleRepository.getById(articleId);

        final CategoryId categoryId = request.id() == null ? null : CategoryId.of(request.id());

        if (Objects.nonNull(categoryId))
            categoryByIdFinder.find(categoryId);

        article.updateCategory(categoryId);

        return ArticleResponse.fromArticle(articleRepository.update(article));
    }

}
