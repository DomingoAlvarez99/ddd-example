package org.dalvarez.ddd_example.category.application;

import org.dalvarez.ddd_example.category.domain.model.Category;
import org.dalvarez.ddd_example.shared.application.response.QueryResultResponse;
import org.dalvarez.ddd_example.shared.domain.criteria.QueryResult;

import java.util.List;

public final class CategoryQueryResultResponse extends QueryResultResponse<Category, CategoryResponse> {


    private CategoryQueryResultResponse(final Long totalElements,
                                        final Long firstElement,
                                        final List<Category> result) {
        super(totalElements, firstElement, result);
    }

    public static CategoryQueryResultResponse of(QueryResult<Category> queryResult) {
        return new CategoryQueryResultResponse(
                queryResult.totalElements(),
                queryResult.firstElement(),
                queryResult.result()
        );
    }

    @Override
    protected CategoryResponse mapToResponse(final Category request) {
        return CategoryResponse.fromCategory(request);
    }

}
