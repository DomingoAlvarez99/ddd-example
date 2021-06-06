package org.dalvarez.shop.core.shared.domain.validation;

public final class Field<T> {

    private static final String UNKNOWN_FIELD_NAME = "?";

    private final String name;

    private final T value;

    public Field(final T value) {
        this.value = value;
        this.name = UNKNOWN_FIELD_NAME;

    }

    public Field(final String name,
                 final T value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

}
