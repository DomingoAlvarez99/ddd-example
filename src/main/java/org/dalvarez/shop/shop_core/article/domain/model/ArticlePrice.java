package org.dalvarez.shop.shop_core.article.domain.model;

import org.dalvarez.shop.shop_core.shop_common.shared.domain.value_object.DoubleValueObject;

import java.util.Objects;

public final class ArticlePrice extends DoubleValueObject {

    private static final double MIN_VALUE = 0;

    private static final double MAX_VALUE = 1000;

    private ArticlePrice() {
        super((Double) null);
    }

    private ArticlePrice(final Double value) {
        super(value);
        ensureIsBetweenAcceptedValues();
    }

    private void ensureIsBetweenAcceptedValues() {
        if (Objects.isNull(value()))
            throw new RuntimeException("Invalid value");

        if (!isBetweenInclusive(MIN_VALUE, MAX_VALUE))
            throw new RuntimeException("Value out of range value");
    }

    public static ArticlePrice of(final Double value) {
        return new ArticlePrice(value);
    }

    public static double minValue() {
        return MIN_VALUE;
    }

    public static double maxValue() {
        return MAX_VALUE;
    }

}
