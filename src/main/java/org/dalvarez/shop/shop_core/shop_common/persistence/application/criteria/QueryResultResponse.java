package org.dalvarez.shop.shop_core.shop_common.persistence.application.criteria;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.QueryResult;

import java.util.List;

public final class QueryResultResponse<T> extends QueryResult<T> {

    public QueryResultResponse() {

    }

    public QueryResultResponse(final Long totalElements,
                               final Long firstElement,
                               final List<T> result) {
        super(totalElements, firstElement, result);
    }

    public QueryResultResponse(final Long totalElements,
                               final List<T> result) {
        super(totalElements, result);
    }

    @JsonIgnore
    @Override
    public T getSingleResult() {
        return super.getSingleResult();
    }

    @JsonIgnore
    @Override
    public T getFirstResult() {
        return super.getFirstResult();
    }

}
