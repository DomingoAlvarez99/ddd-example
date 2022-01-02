package org.dalvarez.ddd_example.article.application.find.by_id;

import org.dalvarez.ddd_example.article.application.ArticleResponse;
import org.dalvarez.ddd_example.article.domain.model.ArticleId;
import org.dalvarez.ddd_example.article.domain.repository.ArticleRepository;

public final class ArticleByIdFinder {

    private final ArticleRepository articleRepository;

    public ArticleByIdFinder(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleResponse find(final String id) {
        return ArticleResponse.fromArticle(articleRepository.getById(ArticleId.of(id)));
    }

}
