package org.dalvarez.shop.shop_core.shop_common.shared.domain.value_object;

public class IntValueObject extends ValueObject<Integer> {

    protected IntValueObject(final String value) {
        this(Integer.parseInt(value));
    }

    protected IntValueObject(final Integer value) {
        super(value);
    }

    public final Boolean isBigger(final IntValueObject other) {
        return isBigger(other.value());
    }

    public final Boolean isBigger(final Integer other) {
        return value() > other;
    }

    public final Boolean isSmaller(final IntValueObject other) {
        return isSmaller(other.value());
    }

    public final Boolean isSmaller(final Integer other) {
        return value() < other;
    }

    public final Boolean isBetweenInclusive(final IntValueObject minValue,
                                            final IntValueObject maxValue) {
        return isBetweenInclusive(minValue.value(), maxValue.value());
    }

    public final Boolean isBetweenInclusive(final Integer minValue,
                                            final Integer maxValue) {
        return !isSmaller(minValue) && !isBigger(maxValue);
    }

    public final Boolean isBetweenExclusive(final IntValueObject minValue,
                                            final IntValueObject maxValue) {
        return isBetweenExclusive(minValue.value(), maxValue.value());
    }

    public final Boolean isBetweenExclusive(final Integer minValue,
                                            final Integer maxValue) {
        return isBigger(minValue) && isSmaller(maxValue);
    }

}
