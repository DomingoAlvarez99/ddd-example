package org.dalvarez.ddd_example.shared.domain.criteria.filter;

import org.dalvarez.ddd_example.shared.domain.value_object.ValueObject;

public class WrongFilterException extends RuntimeException {

    private static final String INVALID_FORMAT_MESSAGE = "The filter <%s> could not be processed, correct format <%s>";

    private static final String INVALID_SUBCLASS_MESSAGE = "Aggregate class <%s> is not a subclass of <%s>";

    private static final String UNKNOWN_FIELD_MESSAGE = "Field <%s> is unknown for the <%s> class";

    public WrongFilterException(final Exception exception) {
        super(exception);
    }

    public WrongFilterException(final String message) {
        super(message);
    }

    public static void throwCauseOfFormatIsInvalid(final String filter) {
        throw new WrongFilterException(String.format(INVALID_FORMAT_MESSAGE, filter, Filter.FILTER_REGEX));
    }

    public static void throwCauseOfAggregateIsNotASubclassOfValueObject(final Class<?> aggregateClass) {
        throw new WrongFilterException(String.format(INVALID_SUBCLASS_MESSAGE, aggregateClass, ValueObject.class));
    }

    public static void throwCauseOfFieldNotExist(final NoSuchFieldException e,
                                                 final Class<?> aggregate) {
        String field = e.getMessage().split(":")[0].trim();

        throw new WrongFilterException(String.format(UNKNOWN_FIELD_MESSAGE, field, aggregate.getSimpleName()));
    }

    public static void throwCauseOf(final Exception e) {
        throw new WrongFilterException(e);
    }

}
