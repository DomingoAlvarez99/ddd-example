package org.dalvarez.ddd_example.shared.domain.criteria.filter;

import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class Filters {

    private static final String QUERY_VALUE_DELIMITER = "&";

    private final List<Filter<?>> values;

    private final FiltersBooleanOperator booleanOperator;

    public Filters() {
        this.values = Collections.emptyList();
        this.booleanOperator = FiltersBooleanOperator.AND;
    }

    public Filters(final List<Filter<?>> values) {
        this.values = values;
        this.booleanOperator = FiltersBooleanOperator.AND;
    }

    public Filters(final Filter<?>... values) {
        this.values = Arrays.asList(values);
        this.booleanOperator = FiltersBooleanOperator.AND;
    }

    public Filters(final List<Filter<?>> values,
                   final FiltersBooleanOperator booleanOperator) {
        this.values = values;
        this.booleanOperator = booleanOperator;
    }

    public Filters(final FiltersBooleanOperator booleanOperator,
                   final Filter<?>... values) {
        this.values = Arrays.asList(values);
        this.booleanOperator = booleanOperator;
    }

    public List<Filter<?>> values() {
        return values;
    }

    public FiltersBooleanOperator booleanOperator() {
        return booleanOperator;
    }

    public static Filters none() {
        return new Filters(Collections.emptyList());
    }

    public boolean hasValues() {
        return !values.isEmpty();
    }

    public static Filters fromQuery(
            final Class<?> aggregateClass,
            final String values,
            final String booleanOperator) {
        return new Filters(fromQueryValues(values, aggregateClass), FiltersBooleanOperator.fromValue(booleanOperator));
    }

    private static List<Filter<?>> fromQueryValues(final String values,
                                                   final Class<?> aggregateClass) {
        return Arrays.stream(Optional.ofNullable(values)
                                     .orElse(Strings.EMPTY)
                                     .split(QUERY_VALUE_DELIMITER))
                     .filter(Predicate.not(String::isBlank))
                     .map(query -> Filter.fromQuery(query, aggregateClass))
                     .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Filters{" +
                "values=" + values +
                ", booleanOperator=" + booleanOperator +
                '}';
    }

}
