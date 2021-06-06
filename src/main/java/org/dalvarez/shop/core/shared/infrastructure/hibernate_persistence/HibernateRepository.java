package org.dalvarez.shop.core.shared.infrastructure.hibernate_persistence;

import org.dalvarez.shop.core.shared.domain.criteria.Criteria;
import org.dalvarez.shop.core.shared.domain.criteria.CriteriaConverter;
import org.dalvarez.shop.core.shared.domain.criteria.filter.Filter;
import org.dalvarez.shop.core.shared.domain.criteria.filter.FilterOperator;
import org.dalvarez.shop.core.shared.domain.query_result.QueryResult;
import org.dalvarez.shop.core.shared.domain.util.CollectionUtils;

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

    protected T update(final Long id,
                       final T entity) {
        ensureAggregationExists(id);

        return save(entity);
    }

    private void ensureAggregationExists(final Long id) {
        findById(id);
    }

    protected T findById(final Long id) {
        return findFirstBy(
                FieldNames.ID,
                id,
                NotFoundException.getIdMessage(aggregateClass, id)
        );
    }

    protected T findByUuid(final String uuid) {
        return findFirstBy(
                FieldNames.UUID,
                uuid,
                NotFoundException.getUuidMessage(aggregateClass, uuid)
        );
    }

    protected <V> T findFirstBy(final String field,
                                final V value) {
        return findFirstBy(
                field,
                value,
                NotFoundException.getDefaultMessage(aggregateClass)
        );
    }

    protected <V> T findFirstBy(final String field,
                                final V value,
                                final String errorMessage) {
        return findByCriteria(
                Criteria.builder()
                        .withFilter(new Filter<>(field, FilterOperator.EQUAL, value))
                        .build(),
                errorMessage
        )
                .getResult()
                .get(0);
    }

    protected void removeById(final Long id) {
        final Optional<T> entity = Optional.ofNullable(
                entityManager.find(aggregateClass, id)
        );

        entity.ifPresent(e -> {
                             entityManager.remove(e);

                             entityManager.flush();
                             entityManager.clear();
                         }
        );
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
        final Long resultsCount = countByCriteria(criteria);

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

    protected Long countByCriteria(final Criteria criteria) {
        final CriteriaQuery<Long> criteriaQuery = criteriaConverter.convertToCountQuery(criteria, aggregateClass);
        final TypedQuery<Long> countQuery = entityManager.createQuery(criteriaQuery);

        return countQuery.getSingleResult();
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

    private static final class FieldNames {

        private static final String ID = "id";

        private static final String UUID = "uuid";

    }

}
