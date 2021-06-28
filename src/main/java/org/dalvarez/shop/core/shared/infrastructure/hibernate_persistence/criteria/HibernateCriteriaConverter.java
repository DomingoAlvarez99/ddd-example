package org.dalvarez.shop.core.shared.infrastructure.hibernate_persistence.criteria;

import org.dalvarez.shop.core.shared.domain.criteria.Criteria;
import org.dalvarez.shop.core.shared.domain.criteria.CriteriaConverter;
import org.dalvarez.shop.core.shared.domain.criteria.filter.Filter;
import org.dalvarez.shop.core.shared.domain.criteria.filter.FilterOperator;
import org.dalvarez.shop.core.shared.domain.criteria.filter.FiltersBooleanOperator;
import org.dalvarez.shop.core.shared.domain.criteria.order.OrderType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class HibernateCriteriaConverter<T> implements CriteriaConverter<T> {

    private final CriteriaBuilder builder;

    private final Map<FilterOperator, BiFunction<Expression<String>, Filter<?>, Predicate>> predicateTransformers = Map.of(
            FilterOperator.EQUAL, this::equalsPredicateTransformer,
            FilterOperator.NOT_EQUAL, this::notEqualsPredicateTransformer,
            FilterOperator.GREATER_THAN, this::greaterThanPredicateTransformer,
            FilterOperator.LESS_THAN, this::lowerThanPredicateTransformer,
            FilterOperator.CONTAINS, this::containsPredicateTransformer,
            FilterOperator.NOT_CONTAINS, this::notContainsPredicateTransformer
    );

    private final Map<OrderType, Function<Path<Object>, Order>> orderTransformers = Map.of(
            OrderType.ASC, this::ascOrderTransformer,
            OrderType.DESC, this::descOrderTransformer
    );

    private final Map<FiltersBooleanOperator, Function<Predicate[], Predicate>> booleanExpressionPredicatesTransformers = Map.of(
            FiltersBooleanOperator.AND, this::andBooleanExpressionPredicatesTransformer,
            FiltersBooleanOperator.OR, this::orBooleanExpressionPredicatesTransformer
    );

    public HibernateCriteriaConverter(final CriteriaBuilder criteriaBuilder) {
        this.builder = criteriaBuilder;
    }

    @Override
    public CriteriaQuery<T> convert(final Criteria criteria,
                                    final Class<T> aggregateClass) {
        final CriteriaQuery<T> hibernateCriteria = builder.createQuery(aggregateClass);
        final Root<T> root = hibernateCriteria.from(aggregateClass);
        final CriteriaQuery<T> queryWithFilters = convertToQueryWithFilters(
                criteria,
                hibernateCriteria,
                root
        );

        if (criteria.getOrder()
                    .hasOrder()) {
            final Path<Object> orderBy = root.get(criteria.getOrder()
                                                          .getField());
            final Order order = formatOrder(
                    criteria.getOrder()
                            .getType(),
                    orderBy
            );

            return queryWithFilters.orderBy(order);
        }

        return queryWithFilters;
    }

    @Override
    public CriteriaQuery<Long> convertToCountQuery(final Criteria criteria,
                                                   final Class<T> aggregateClass) {
        final CriteriaQuery<Long> hibernateCriteria = builder.createQuery(Long.class);
        final Root<T> root = hibernateCriteria.from(aggregateClass);
        final CriteriaQuery<Long> queryWithFilters = convertToQueryWithFilters(
                criteria,
                hibernateCriteria,
                root
        );

        return queryWithFilters.select(builder.count(root));
    }

    @Override
    public <S extends Number> CriteriaQuery<S> convertToSumQuery(final Criteria criteria,
                                                                 final String fieldName,
                                                                 final Class<T> aggregateClass,
                                                                 final Class<S> sumClass) {
        final CriteriaQuery<S> hibernateCriteria = builder.createQuery(sumClass);
        final Root<T> root = hibernateCriteria.from(aggregateClass);
        final CriteriaQuery<S> queryWithFilters = convertToQueryWithFilters(
                criteria,
                hibernateCriteria,
                root
        );

        return queryWithFilters.select(builder.sum(root.get(fieldName)));
    }

    @Override
    public CriteriaDelete<T> convertToCriteriaDelete(final Criteria criteria,
                                                     final Class<T> aggregateClass) {
        final CriteriaDelete<T> hibernateCriteria = builder.createCriteriaDelete(aggregateClass);
        final Root<T> root = hibernateCriteria.from(aggregateClass);

        final Predicate[] predicates = formatPredicates(
                criteria.getFilters()
                        .getValues(),
                root
        );

        return hibernateCriteria.where(formatBooleanExpressionPredicates(
                criteria.getFilters()
                        .getBooleanOperator(),
                predicates
        ));
    }

    private <V> CriteriaQuery<V> convertToQueryWithFilters(final Criteria criteria,
                                                           final CriteriaQuery<V> hibernateCriteria,
                                                           final Root<T> root) {
        final Predicate[] predicates = formatPredicates(
                criteria.getFilters()
                        .getValues(),
                root
        );

        return hibernateCriteria.where(formatBooleanExpressionPredicates(
                criteria.getFilters()
                        .getBooleanOperator(),
                predicates
        ));
    }

    private Predicate formatBooleanExpressionPredicates(final FiltersBooleanOperator booleanOperator,
                                                        final Predicate[] predicates) {
        final Function<Predicate[], Predicate> transformer = booleanExpressionPredicatesTransformers.get(booleanOperator);

        return transformer.apply(predicates);
    }

    private Predicate andBooleanExpressionPredicatesTransformer(final Predicate[] predicates) {
        return builder.and(predicates);
    }

    private Predicate orBooleanExpressionPredicatesTransformer(final Predicate[] predicates) {
        return builder.or(predicates);
    }

    private Order formatOrder(final OrderType orderType,
                              final Path<Object> orderBy) {
        final Function<Path<Object>, javax.persistence.criteria.Order> transformer = orderTransformers.get(orderType);

        return transformer.apply(orderBy);
    }

    private Order descOrderTransformer(final Path<Object> orderBy) {
        return builder.desc(orderBy);
    }

    private Order ascOrderTransformer(final Path<Object> orderBy) {
        return builder.asc(orderBy);
    }

    private Predicate[] formatPredicates(final List<Filter<?>> filters,
                                         final Root<T> root) {
        return filters.stream()
                      .map(filter -> formatPredicate(
                              filter,
                              root
                      ))
                      .collect(Collectors.toList())
                      .toArray(Predicate[]::new);
    }

    private Expression<String> navigateTo(final Filter<?> filter,
                                          final Root<T> root) {
        if (filter.getFieldPath()
                  .size() == 1)
            return root.get(filter.getLastFieldPath());

        final AtomicReference<Path<?>> path = new AtomicReference<>();

        path.set(root);

        IntStream.range(
                0,
                filter.getFieldPath()
                      .size() - 1
        )
                 .boxed()
                 .forEach(position -> path.set(path.get()
                                                   .get((filter.getFieldPath()
                                                               .get(position)))));

        return path.get()
                   .get(filter.getLastFieldPath());
    }

    private Predicate formatPredicate(final Filter<?> filter,
                                      final Root<T> root) {
        final BiFunction<Expression<String>, Filter<?>, Predicate> transformer = predicateTransformers.get(
                filter.getOperator());

        return transformer.apply(
                navigateTo(filter, root),
                filter
        );
    }

    private Predicate equalsPredicateTransformer(final Expression<String> filter,
                                                 final Filter<?> value) {
        return builder.equal(
                filter,
                value.getValue()
        );
    }

    private Predicate notEqualsPredicateTransformer(final Expression<String> filter,
                                                    final Filter<?> value) {
        return builder.notEqual(
                filter,
                value.getValue()
        );
    }

    private Predicate greaterThanPredicateTransformer(final Expression<String> filterExpression,
                                                      final Filter<?> filter) {
        return builder.greaterThan((Expression<? extends Comparable>) filterExpression, (Comparable) filter.getValue());
    }

    private Predicate lowerThanPredicateTransformer(final Expression<String> filterExpression,
                                                    final Filter<?> filter) {
        return builder.lessThan((Expression<? extends Comparable>) filterExpression, (Comparable) filter.getValue());
    }

    private Predicate containsPredicateTransformer(final Expression<String> filter,
                                                   final Filter<?> value) {
        return builder.like(
                filter,
                String.format("%%%s%%", value.getValue())
        );
    }

    private Predicate notContainsPredicateTransformer(final Expression<String> filter,
                                                      final Filter<?> value) {
        return builder.notLike(
                filter,
                String.format("%%%s%%", value.getValue())
        );
    }

}
