package org.dalvarez.ddd_example.article.application.find.by_criteria;

import org.dalvarez.ddd_example.article.application.ArticleQueryResultResponse;
import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.article.domain.repository.ArticleRepository;
import org.dalvarez.ddd_example.shared.domain.criteria.Criteria;
import org.dalvarez.ddd_example.shared.domain.criteria.QueryResult;
import org.dalvarez.ddd_example.shared.infrastructure.shared.exception.NotFoundException;

public final class ArticleByCriteriaFinder {

    private final ArticleRepository articleRepository;

    public ArticleByCriteriaFinder(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleQueryResultResponse find(final Criteria criteria) {
        final QueryResult<Article> queryResult = articleRepository.getByCriteria(criteria);

        if (queryResult.result().isEmpty())
            throw new NotFoundException(Article.class);

        return ArticleQueryResultResponse.of(queryResult);
    }

}
