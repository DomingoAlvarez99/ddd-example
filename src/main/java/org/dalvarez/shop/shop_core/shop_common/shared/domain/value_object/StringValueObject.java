package org.dalvarez.shop.shop_core.shop_common.shared.domain.value_object;

import java.util.Objects;

public class StringValueObject extends ValueObject<String> {

    protected StringValueObject(final String value) {
        super(value);
    }

    public boolean isNullOrBlank() {
        return Objects.isNull(value()) || value().isBlank();
    }

    public final Boolean hasMoreLength(final int length) {
        return value().length() > length;
    }

    public final Boolean hasMoreLength(final String other) {
        return hasMoreLength(other.length());
    }

    public final Boolean hasMoreLength(final StringValueObject other) {
        return hasMoreLength(other.value());
    }

    public final Boolean hasLessLength(final int length) {
        return value().length() < length;
    }

    public final Boolean hasLessLength(final String other) {
        return hasLessLength(other.length());
    }

    public final Boolean hasLessLength(final StringValueObject other) {
        return hasLessLength(other.value());
    }

    public final Boolean isLengthBetweenInclusive(final int minValue,
                                            final int maxValue) {
        return !hasLessLength(minValue) && !hasMoreLength(maxValue);
    }

    public final Boolean isLengthBetweenInclusive(final String minValue,
                                            final String maxValue) {
        return isLengthBetweenInclusive(minValue.length(), maxValue.length());
    }

    public final Boolean isBetweenInclusive(final StringValueObject minValue,
                                            final StringValueObject maxValue) {
        return isLengthBetweenInclusive(minValue.value(), maxValue.value());
    }

    public final Boolean isLengthBetweenExclusive(final int minValue,
                                                  final int maxValue) {
        return hasMoreLength(minValue) && hasLessLength(maxValue);
    }

    public final Boolean isLengthBetweenExclusive(final String minValue,
                                                  final String maxValue) {
        return isLengthBetweenExclusive(minValue.length(), maxValue.length());
    }

    public final Boolean isLengthBetweenExclusive(final StringValueObject minValue,
                                                  final StringValueObject maxValue) {
        return isLengthBetweenExclusive(minValue.value(), maxValue.value());
    }

}
