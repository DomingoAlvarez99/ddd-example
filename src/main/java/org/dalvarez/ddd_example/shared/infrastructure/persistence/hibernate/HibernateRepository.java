package org.dalvarez.ddd_example.shared.infrastructure.persistence.hibernate;

import org.dalvarez.ddd_example.shared.domain.criteria.CountResult;
import org.dalvarez.ddd_example.shared.domain.criteria.Criteria;
import org.dalvarez.ddd_example.shared.domain.criteria.CriteriaConverter;
import org.dalvarez.ddd_example.shared.domain.criteria.QueryResult;
import org.dalvarez.ddd_example.shared.domain.criteria.filter.Filter;
import org.dalvarez.ddd_example.shared.domain.criteria.filter.FilterOperator;
import org.dalvarez.ddd_example.shared.domain.value_object.id.Identifier;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
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

    protected void save(final T entity) {
        entityManager.persist(entity);

        entityManager.flush();
        entityManager.clear();
    }

    protected void update(final T entity) {
        entityManager.merge(entity);

        entityManager.flush();
        entityManager.clear();
    }

    protected Optional<T> findById(final Identifier id) {
        return findOneBy(
                ID_FIELD,
                id
        );
    }

    protected <V> Optional<T> findOneBy(final String field,
                                        final V value) {
        return findByCriteria(
                Criteria.builder()
                        .withFilter(new Filter<>(field, FilterOperator.EQUAL, value))
                        .build()
        ).singleResult();
    }

    protected void removeById(final Identifier id) {
        final Filter<Identifier> idFilter = new Filter<>(ID_FIELD, FilterOperator.EQUAL, id);
        final Criteria criteriaDeleteById = Criteria.builder()
                                                    .withFilter(idFilter)
                                                    .build();
        removeByCriteria(criteriaDeleteById);
    }

    protected void removeByCriteria(final Criteria criteria) {
        final CriteriaDelete<T> hibernateCriteria = criteriaConverter.convertToCriteriaDelete(
                criteria,
                aggregateClass
        );

        entityManager.createQuery(hibernateCriteria).executeUpdate();
    }

    protected QueryResult<T> findByCriteria(final Criteria criteria) {
        final CriteriaQuery<T> hibernateCriteria = criteriaConverter.convert(
                criteria,
                aggregateClass
        );
        final TypedQuery<T> query = entityManager.createQuery(hibernateCriteria);
        final Long resultsCount = countByCriteria(criteria).total();

        final List<T> results = paginate(query, criteria).getResultStream()
                                                         .collect(Collectors.toList());

        return new QueryResult<>(resultsCount, results);
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

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    private TypedQuery<T> paginate(final TypedQuery<T> query,
                                   final Criteria criteria) {
        final int pageSize = criteria.page().getSize().intValue();
        final int pageIndex = criteria.page().getIndex().intValue();

        return query.setFirstResult(pageIndex * pageSize).setMaxResults(pageSize);
    }

}
