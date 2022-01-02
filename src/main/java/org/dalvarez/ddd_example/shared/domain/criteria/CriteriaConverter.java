package org.dalvarez.ddd_example.shared.domain.criteria;

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
