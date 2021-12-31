package org.dalvarez.shop.shop_core.shop_common.persistence.infrastructure.hibernate;

import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.CountResult;
import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.Criteria;
import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.CriteriaConverter;
import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.QueryResult;
import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.filter.Filter;
import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.filter.FilterOperator;
import org.dalvarez.shop.shop_core.shop_common.persistence.infrastructure.shared.exception.NotFoundException;
import org.dalvarez.shop.shop_core.shop_common.shared.domain.util.CollectionUtils;
import org.dalvarez.shop.shop_core.shop_common.shared.domain.value_object.id.Identifier;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
public abstract class HibernateRepository<T> {

    private static final String ID_FIELD = "id";

    protected final EntityManager entityManager;

    protected final CriteriaConverter<T> criteriaConverter;

    protected final Class<T> aggregateClass;

    protected HibernateRepository(final EntityManager entityManager,
                                  final CriteriaConverter<T> criteriaConverter,
                                  final Class<T> aggregateClass) {
        this.entityManager = entityManager;
        this.criteriaConverter = criteriaConverter;
        this.aggregateClass = aggregateClass;
    }

    protected T save(final T entity) {
        final T persistedObject = entityManager.merge(entity);

        entityManager.flush();
        entityManager.clear();

        return persistedObject;
    }

    protected T update(final T entity) {
        return save(entity);
    }

    protected T findById(final Identifier id) {
        return findOneBy(
                ID_FIELD,
                id,
                NotFoundException.getIdMessage(aggregateClass, id)
        );
    }

    protected <V> T findOneBy(final String field,
                              final V value) {
        return findOneBy(
                field,
                value,
                NotFoundException.getDefaultMessage(aggregateClass)
        );
    }

    protected <V> T findOneBy(final String field,
                              final V value,
                              final String errorMessage) {
        return findByCriteria(
                Criteria.builder()
                        .withFilter(new Filter<>(field, FilterOperator.EQUAL, value))
                        .build(),
                errorMessage
        )
                .getSingleResult();
    }

    protected void removeById(final Identifier id) {
        Filter<Identifier> idFilter = new Filter<>(ID_FIELD, FilterOperator.EQUAL, id);
        Criteria criteriaDeleteById = Criteria.builder()
                                               .withFilter(idFilter)
                                               .build();
        removeByCriteria(criteriaDeleteById);
    }

    protected void removeByCriteria(final Criteria criteria) {
        final CriteriaDelete<T> hibernateCriteria = criteriaConverter.convertToCriteriaDelete(
                criteria,
                aggregateClass
        );

        entityManager.createQuery(hibernateCriteria)
                     .executeUpdate();
    }

    private QueryResult<T> findByCriteria(final Criteria criteria,
                                          final String errorMessage) {
        final CriteriaQuery<T> hibernateCriteria = criteriaConverter.convert(
                criteria,
                aggregateClass
        );
        final TypedQuery<T> query = entityManager.createQuery(hibernateCriteria);
        final Long resultsCount = countByCriteria(criteria).getTotal();

        final List<T> results = paginate(query, criteria).getResultStream()
                                                         .collect(Collectors.collectingAndThen(
                                                                 Collectors.toList(),
                                                                 Optional::of
                                                         ))
                                                         .filter(CollectionUtils::nonEmpty)
                                                         .orElseThrow(() -> new NotFoundException(errorMessage));

        return new QueryResult<>(
                resultsCount,
                results
        );
    }

    protected QueryResult<T> findByCriteria(final Criteria criteria) {
        return findByCriteria(criteria, NotFoundException.getDefaultMessage(aggregateClass));
    }

    protected CountResult countByCriteria(final Criteria criteria) {
        final CriteriaQuery<Long> criteriaQuery = criteriaConverter.convertToCountQuery(criteria, aggregateClass);
        final TypedQuery<Long> countQuery = entityManager.createQuery(criteriaQuery);

        return new CountResult(countQuery.getSingleResult());
    }

    protected <S extends Number> S sumByCriteria(final Criteria criteria,
                                                 final String fieldName,
                                                 final Class<S> sumClass) {
        final CriteriaQuery<S> criteriaQuery = criteriaConverter.convertToSumQuery(
                criteria,
                fieldName,
                aggregateClass,
                sumClass
        );

        return entityManager.createQuery(criteriaQuery)
                            .getSingleResult();
    }

    private TypedQuery<T> paginate(final TypedQuery<T> query,
                                   final Criteria criteria) {
        final int pageSize = criteria.getPage()
                                     .getSize()
                                     .intValue();
        final int pageIndex = criteria.getPage()
                                      .getIndex()
                                      .intValue();

        return query.setFirstResult(pageIndex * pageSize)
                    .setMaxResults(pageSize);
    }

}
