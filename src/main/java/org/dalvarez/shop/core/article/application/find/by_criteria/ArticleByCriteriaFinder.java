package org.dalvarez.shop.core.article.application.find.by_criteria;

import org.dalvarez.shop.core.article.application.ArticleResponse;
import org.dalvarez.shop.core.article.domain.Article;
import org.dalvarez.shop.core.article.domain.ArticleRepository;
import org.dalvarez.shop.shared.persistence.application.criteria.QueryResultResponse;
import org.dalvarez.shop.shared.persistence.domain.criteria.Criteria;
import org.dalvarez.shop.shared.persistence.domain.criteria.QueryResult;

import java.util.stream.Collectors;

public final class ArticleByCriteriaFinder {

    private final ArticleRepository articleRepository;

    public ArticleByCriteriaFinder(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public QueryResultResponse<ArticleResponse> find(final Criteria criteria) {
        final QueryResult<Article> queryResult = articleRepository.getByCriteria(criteria);

        return new QueryResultResponse<>(
                queryResult.getTotalElements(),
                queryResult.getFirstElement(),
                queryResult.getResult()
                           .stream()
                           .map(ArticleResponse::fromArticle)
                           .collect(Collectors.toList())
        );
    }

}
