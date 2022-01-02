package org.dalvarez.ddd_example.shared.domain.criteria;

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

    public Long totalElements() {
        return totalElements;
    }

    public Long firstElement() {
        return firstElement;
    }

    public List<T> result() {
        return result;
    }

    public T singleResult() {
        if (result.size() > 1)
            throw new NonUniqueResultException();

        return result.get(0);
    }

    public T firstResult() {
        return result.get(0);
    }

}
