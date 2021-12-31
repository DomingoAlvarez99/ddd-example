package org.dalvarez.shop.shop_core.shop_common.persistence.domain.repository;

import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.CountResult;
import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.Criteria;
import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.QueryResult;
import org.dalvarez.shop.shop_core.shop_common.shared.domain.value_object.id.Identifier;

public interface GenericRepository<T> {

    T getById(final Identifier id);

    QueryResult<T> getByCriteria(final Criteria criteria);

    CountResult countByCriteria(final Criteria criteria);

    T create(final T model);

    T update(final T model);

    void deleteById(final Identifier id);

    void deleteByCriteria(final Criteria criteria);

}
