package org.dalvarez.shop.core.shared.domain.repository;

import org.dalvarez.shop.core.shared.domain.criteria.Criteria;
import org.dalvarez.shop.core.shared.domain.query_result.QueryResult;

public interface GenericRepository<T, ID> {

    T getById(final ID id);

    T getByUuid(final String uuid);

    QueryResult<T> getByCriteria(final Criteria criteria);

    T create(final T model);

    T update(final T model);

    void deleteById(final ID id);

    void deleteByCriteria(final Criteria criteria);

}
