package org.dalvarez.shop.core.shared.domain.query_result;

import java.util.Collections;
import java.util.List;

public final class QueryResult<T> {

    private final Long totalElements;

    private final Long firstElement;

    private final List<T> result;

    public QueryResult() {
        totalElements = 0L;
        firstElement = 0L;
        result = Collections.emptyList();
    }

    public QueryResult(final Long totalElements,
                       final Long firstElement,
                       final List<T> result) {
        this.totalElements = totalElements;
        this.firstElement = firstElement;
        this.result = result;
    }

    public QueryResult(final Long totalElements,
                       final List<T> result) {
        this.totalElements = totalElements;
        this.firstElement = 0L;
        this.result = result;
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

}
