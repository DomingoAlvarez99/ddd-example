package org.dalvarez.shop.shop_core.shop_common.shared.domain.value_object;

public class DoubleValueObject extends ValueObject<Double> {

    protected DoubleValueObject(final String value) {
        this(Double.parseDouble(value));
    }

    protected DoubleValueObject(final Double value) {
        super(value);
    }

    public final Boolean isBigger(final Double other) {
        return value() > other;
    }

    public final Boolean isBigger(final DoubleValueObject other) {
        return isBigger(other.value());
    }

    public final Boolean isSmaller(final Double other) {
        return value() < other;
    }

    public final Boolean isSmaller(final DoubleValueObject other) {
        return isSmaller(other.value());
    }

    public final Boolean isBetweenInclusive(final Double minValue,
                                            final Double maxValue) {
        return !isSmaller(minValue) && !isBigger(maxValue);
    }

    public final Boolean isBetweenInclusive(final DoubleValueObject minValue,
                                            final DoubleValueObject maxValue) {
        return isBetweenInclusive(minValue.value(), maxValue.value());
    }

    public final Boolean isBetweenExclusive(final Double minValue,
                                            final Double maxValue) {
        return isBigger(minValue) && isSmaller(maxValue);
    }

    public final Boolean isBetweenExclusive(final DoubleValueObject minValue,
                                            final DoubleValueObject maxValue) {
        return isBetweenExclusive(minValue.value(), maxValue.value());
    }

}
