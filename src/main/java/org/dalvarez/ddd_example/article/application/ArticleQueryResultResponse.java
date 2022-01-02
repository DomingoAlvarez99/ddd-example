package org.dalvarez.ddd_example.article.application;

import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.shared.application.response.QueryResultResponse;
import org.dalvarez.ddd_example.shared.domain.criteria.QueryResult;

import java.util.List;

public final class ArticleQueryResultResponse extends QueryResultResponse<Article, ArticleResponse> {

    private ArticleQueryResultResponse() {

    }

    private ArticleQueryResultResponse(final Long totalElements,
                                       final Long firstElement,
                                       final List<Article> result) {
        super(totalElements, firstElement, result);
    }

    public static ArticleQueryResultResponse of(QueryResult<Article> queryResult) {
        return new ArticleQueryResultResponse(
                queryResult.totalElements(),
                queryResult.firstElement(),
                queryResult.result()
        );
    }

    @Override
    protected ArticleResponse mapToResponse(final Article request) {
        return ArticleResponse.fromArticle(request);
    }

}
