package org.dalvarez.ddd_example.shared.domain.repository;

import org.dalvarez.ddd_example.shared.domain.criteria.CountResult;
import org.dalvarez.ddd_example.shared.domain.criteria.Criteria;
import org.dalvarez.ddd_example.shared.domain.criteria.QueryResult;
import org.dalvarez.ddd_example.shared.domain.value_object.id.Identifier;

import java.util.Optional;

public interface GenericRepository<T> {

    Optional<T> getById(final Identifier id);

    QueryResult<T> getByCriteria(final Criteria criteria);

    CountResult countByCriteria(final Criteria criteria);

    void createOrUpdate(final T model);

    void deleteById(final Identifier id);

    void deleteByCriteria(final Criteria criteria);

}
