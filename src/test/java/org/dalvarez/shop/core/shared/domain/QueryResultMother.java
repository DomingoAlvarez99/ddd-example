package org.dalvarez.shop.core.shared.domain;

import org.dalvarez.shop.core.shared.domain.query_result.QueryResult;

import java.util.List;

public class QueryResultMother {

    public static <T> QueryResult<T> fromList(final List<T> items) {
        return new QueryResult<>((long) items.size(), 0L, items);
    }

}
