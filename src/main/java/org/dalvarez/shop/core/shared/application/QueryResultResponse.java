package org.dalvarez.shop.core.shared.application;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.dalvarez.shop.core.shared.domain.exception.NonUniqueResultException;
import org.dalvarez.shop.core.shared.domain.query_result.QueryResult;

import java.util.Collections;
import java.util.List;

public final class QueryResultResponse<T> {

    private final Long totalElements;

    private final Long firstElement;

    private final List<T> result;

    public QueryResultResponse() {
        totalElements = 0L;
        firstElement = 0L;
        result = Collections.emptyList();
    }

    public QueryResultResponse(final Long totalElements,
                               final Long firstElement,
                               final List<T> result) {
        this.totalElements = totalElements;
        this.firstElement = firstElement;
        this.result = result;
    }

    public QueryResultResponse(final Long totalElements,
                               final List<T> result) {
        this.totalElements = totalElements;
        this.firstElement = 0L;
        this.result = result;
    }

    public static <T> QueryResultResponse<T> fromQueryResult(QueryResult<T> queryResult){
        return new QueryResultResponse<>(
                queryResult.getTotalElements(),
                queryResult.getFirstElement(),
                queryResult.getResult()
        );
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public Long getFirstElement() {
        return firstElement;
    }

    public List<T> getResult() {
        return result;
    }

    @JsonIgnore
    public T getSingleResult() {
        if (result.size() > 1)
            throw new NonUniqueResultException();

        return result.get(0);
    }

    @JsonIgnore
    public T getFirstResult() {
        return result.get(0);
    }

}
