package org.dalvarez.shop.core.shared.domain.criteria.filter;

import org.dalvarez.shop.core.shared.domain.exception.WrongFilterException;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class Filter<T> {

    private static final int FIELD_IDX = 1;

    private static final int OPERATOR_IDX = 2;

    private static final int VALUE_IDX = 3;

    private static final int EXPECTED_FILTER_PARTS = List.of(FIELD_IDX, OPERATOR_IDX, VALUE_IDX)
                                                         .size();

    public static final String FILTER_REGEX = "(.*)~(.*)=(.*)";

    private static final String INNER_FIELDS_DELIMITER = ".";

    private static final String INNER_FIELDS_DELIMITER_RE = "\\" + INNER_FIELDS_DELIMITER;

    private static final String MULTIPLE_VALUES_DELIMITER = "|";

    private static final String MULTIPLE_VALUES_DELIMITER_RE = "\\" + MULTIPLE_VALUES_DELIMITER;

    private final String field;

    private final List<String> fieldPath;

    private final FilterOperator operator;

    private final T value;

    private final Set<String> values;

    public Filter(final String field,
                  final FilterOperator operator,
                  final T value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
        fieldPath = calculateFieldPath();
        values = calculateValues();
    }

    public String getField() {
        return field;
    }

    public FilterOperator getOperator() {
        return operator;
    }

    public T getValue() {
        return value;
    }

    public Set<String> calculateValues() {
        if (value == null)
            return new HashSet<>();

        if (!value.toString().contains(MULTIPLE_VALUES_DELIMITER))
            return new HashSet<>(Collections.singletonList(value.toString()));

            return Arrays.stream(value.toString()
                        .split(MULTIPLE_VALUES_DELIMITER_RE))
                         .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
    }

    public Set<String> getValues() {
        return values;
    }

    public static Filter<?> fromQuery(final String filter) {
        final Pattern regex = Pattern.compile(FILTER_REGEX);
        final Matcher matcher = regex.matcher(filter);

        if (!matcher.find() || matcher.groupCount() != EXPECTED_FILTER_PARTS)
            throw new WrongFilterException(filter);

        final String field = matcher.group(FIELD_IDX);
        final String operator = matcher.group(OPERATOR_IDX);
        final String value = matcher.group(VALUE_IDX);

        final FilterOperator filterOperator = FilterOperator.fromValue(operator);

        return new Filter<>(
                field,
                filterOperator,
                value
        );
    }

    private List<String> calculateFieldPath() {
        if (!field.contains(INNER_FIELDS_DELIMITER))
            return Collections.singletonList(field);

        return Arrays.stream(field.split(INNER_FIELDS_DELIMITER_RE))
                     .collect(Collectors.toList());
    }

    public List<String> getFieldPath() {
        return fieldPath;
    }

    public String getLastFieldPath() {
        return fieldPath.get(getFieldPath().size() - 1);
    }

    @Override
    public String toString() {
        return "Filter{" +
                "field='" + field + '\'' +
                ", operator=" + operator +
                ", value=" + value +
                '}';
    }

}
