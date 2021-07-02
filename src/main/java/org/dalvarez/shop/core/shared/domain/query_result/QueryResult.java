package org.dalvarez.shop.core.shared.domain.query_result;

import org.dalvarez.shop.core.shared.domain.exception.NonUniqueResultException;

import java.util.Collections;
import java.util.List;

public class QueryResult<T> {

    protected final Long totalElements;

    protected final Long firstElement;

    protected final List<T> result;

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

    public T getSingleResult() {
        if (result.size() > 1)
            throw new NonUniqueResultException();

        return result.get(0);
    }

    public T getFirstResult() {
        return result.get(0);
    }

}
