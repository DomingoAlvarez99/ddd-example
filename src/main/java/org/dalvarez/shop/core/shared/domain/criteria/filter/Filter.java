package org.dalvarez.shop.core.shared.domain.criteria.filter;

import org.dalvarez.shop.core.shared.domain.exception.BadRequestException;
import org.dalvarez.shop.core.shared.domain.exception.UnknownFieldNameException;
import org.dalvarez.shop.core.shared.domain.validation.Field;
import org.dalvarez.shop.core.shared.domain.validation.FieldValidator;
import org.dalvarez.shop.core.shared.domain.validation.InvalidObjectException;
import org.dalvarez.shop.core.shared.domain.validation.NotNullValidator;
import org.dalvarez.shop.core.shared.domain.validation.ValidationNotPassedException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Filter<T> {

    private static final FieldValidator notNullValidator = NotNullValidator.getInstance();

    private static final String QUERY_ATTRIBUTE_DELIMITER = "&";

    private static final String QUERY_VALUE_DELIMITER = "=";

    private static final Integer KEY_VALUE_EXPECTED_LENGTH = 2;

    private final String field;

    private final FilterOperator operator;

    private final T value;


    public Filter(final String field,
                  final FilterOperator operator,
                  final T value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
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

    public static Filter<?> fromQuery(final String filter) {
        final Map<String, String> attributes = Arrays.stream(filter.split(QUERY_ATTRIBUTE_DELIMITER))
                                                     .map(a -> a.split(QUERY_VALUE_DELIMITER))
                                                     .filter(a -> a.length == KEY_VALUE_EXPECTED_LENGTH)
                                                     .filter(a -> Objects.nonNull(a[0]))
                                                     .collect(Collectors.toMap(a -> a[0], a -> a[1]));

        if (!attributes.containsKey(FieldNames.FIELD))
            throw new UnknownFieldNameException(Filter.class, FieldNames.FIELD);

        final String field = attributes.get(FieldNames.FIELD);

        try {
            notNullValidator.validate(new Field<>(FieldNames.FIELD, field));
        } catch (ValidationNotPassedException e) {
            throw new BadRequestException(new InvalidObjectException(Filter.class, List.of(e.getMessage())));
        }

        final FilterOperator filterOperator = FilterOperator.fromValue(attributes.get(FieldNames.OPERATOR));

        final String value = attributes.get(FieldNames.VALUE);

        return new Filter<>(
                field,
                filterOperator,
                value
        );
    }

    @Override
    public String toString() {
        return "Filter{" +
                "field='" + field + '\'' +
                ", operator=" + operator +
                ", value=" + value +
                '}';
    }

    private static class FieldNames {

        private static final String FIELD = "field";

        private static final String OPERATOR = "operator";

        private static final String VALUE = "value";

    }

}
