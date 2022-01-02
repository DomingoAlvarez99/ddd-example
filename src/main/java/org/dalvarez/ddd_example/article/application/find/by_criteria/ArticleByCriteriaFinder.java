package org.dalvarez.ddd_example.article.application.find.by_criteria;

import org.dalvarez.ddd_example.article.application.ArticleQueryResultResponse;
import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.article.domain.repository.ArticleRepository;
import org.dalvarez.ddd_example.shared.domain.criteria.Criteria;
import org.dalvarez.ddd_example.shared.domain.criteria.QueryResult;

public final class ArticleByCriteriaFinder {

    private final ArticleRepository articleRepository;

    public ArticleByCriteriaFinder(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleQueryResultResponse find(final Criteria criteria) {
        final QueryResult<Article> queryResult = articleRepository.getByCriteria(criteria);

        return ArticleQueryResultResponse.of(queryResult);
    }

}
