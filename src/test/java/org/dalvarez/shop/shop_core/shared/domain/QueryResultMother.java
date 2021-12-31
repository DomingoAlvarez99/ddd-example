package org.dalvarez.shop.shop_core.shared.domain;

import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.QueryResult;

import java.util.List;

public class QueryResultMother {

    public static <T> QueryResult<T> fromList(final List<T> items) {
        return new QueryResult<>((long) items.size(), 0L, items);
    }

}
