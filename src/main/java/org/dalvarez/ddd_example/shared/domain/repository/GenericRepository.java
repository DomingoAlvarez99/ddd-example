package org.dalvarez.ddd_example.shared.domain.repository;

import org.dalvarez.ddd_example.shared.domain.criteria.CountResult;
import org.dalvarez.ddd_example.shared.domain.criteria.Criteria;
import org.dalvarez.ddd_example.shared.domain.criteria.QueryResult;
import org.dalvarez.ddd_example.shared.domain.value_object.id.Identifier;

public interface GenericRepository<T> {

    T getById(final Identifier id);

    QueryResult<T> getByCriteria(final Criteria criteria);

    CountResult countByCriteria(final Criteria criteria);

    T create(final T model);

    T update(final T model);

    void deleteById(final Identifier id);

    void deleteByCriteria(final Criteria criteria);

}
