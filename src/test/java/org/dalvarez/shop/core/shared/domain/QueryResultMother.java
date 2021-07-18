package org.dalvarez.shop.core.shared.domain;

import org.dalvarez.shop.shared.persistence.domain.criteria.QueryResult;

import java.util.List;

public class QueryResultMother {

    public static <T> QueryResult<T> fromList(final List<T> items) {
        return new QueryResult<>((long) items.size(), 0L, items);
    }

}
