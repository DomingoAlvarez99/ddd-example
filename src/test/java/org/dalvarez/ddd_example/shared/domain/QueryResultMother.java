package org.dalvarez.ddd_example.shared.domain;

import org.dalvarez.ddd_example.shared.domain.criteria.QueryResult;

import java.util.List;

public class QueryResultMother {

    public static <T> QueryResult<T> fromList(final List<T> items) {
        return new QueryResult<>((long) items.size(), 0L, items);
    }

}
