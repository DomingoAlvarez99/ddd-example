package org.dalvarez.shop.core.shared.domain.criteria.filter;

import org.apache.logging.log4j.util.Strings;
import org.dalvarez.shop.core.shared.domain.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
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

    public List<Filter<?>> getValues() {
        return values;
    }

    public FiltersBooleanOperator getBooleanOperator() {
        return booleanOperator;
    }

    public static Filters none() {
        return new Filters(Collections.emptyList());
    }

    public boolean hasValues() {
        return !values.isEmpty();
    }

    public static Filters fromQuery(final String values,
                                    final String booleanOperator) {
        return new Filters(fromQueryValues(values), FiltersBooleanOperator.fromValue(booleanOperator));
    }

    private static List<Filter<?>> fromQueryValues(final String values) {
        return Arrays.stream(Optional.ofNullable(values)
                                     .orElse(Strings.EMPTY)
                                     .split(QUERY_VALUE_DELIMITER))
                     .filter(StringUtils::nonEmpty)
                     .map((Function<String, ? extends Filter<?>>) Filter::fromQuery)
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
