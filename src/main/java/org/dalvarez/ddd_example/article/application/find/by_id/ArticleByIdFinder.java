package org.dalvarez.ddd_example.article.application.find.by_id;

import org.dalvarez.ddd_example.article.application.ArticleResponse;
import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.article.domain.model.ArticleId;
import org.dalvarez.ddd_example.article.domain.repository.ArticleRepository;
import org.dalvarez.ddd_example.shared.infrastructure.shared.exception.NotFoundException;

public final class ArticleByIdFinder {

    private final ArticleRepository articleRepository;

    public ArticleByIdFinder(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleResponse find(final String id) {
        ArticleId articleId = ArticleId.of(id);
        Article articleFound = articleRepository.getById(ArticleId.of(id))
                                                .orElseThrow(() -> NotFoundException.build(Article.class, articleId));

        return ArticleResponse.fromArticle(articleFound);
    }

}
