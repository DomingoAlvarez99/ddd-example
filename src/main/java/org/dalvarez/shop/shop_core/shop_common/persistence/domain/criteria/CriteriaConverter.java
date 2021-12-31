package org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria;

import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;

public interface CriteriaConverter<T> {

    CriteriaQuery<T> convert(final Criteria criteria,
                             final Class<T> aggregateClass);

    CriteriaQuery<Long> convertToCountQuery(final Criteria criteria,
                                            final Class<T> aggregateClass);

    <S extends Number> CriteriaQuery<S> convertToSumQuery(final Criteria criteria,
                                                          final String fieldName,
                                                          final Class<T> aggregateClass,
                                                          final Class<S> sumClass);

    CriteriaDelete<T> convertToCriteriaDelete(final Criteria criteria,
                                              final Class<T> aggregateClass);

}
