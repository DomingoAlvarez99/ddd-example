package org.dalvarez.shop.shop_core.article.domain.model;

import org.dalvarez.shop.shop_common.shared.domain.value_object.IntValueObject;

public final class ArticleStock extends IntValueObject {

    private static final int MIN_VALUE = 1;

    private static final int MAX_VALUE = 100;

    private ArticleStock() {
        super((Integer) null);
    }

    private ArticleStock(final Integer value) {
        super(value);
    }

    private void ensureIsBetweenAcceptedValues(final Integer value) {
        /*if (value == null)
            throw new RuntimeException("Invalid value");

        if (!isBetweenExclusive(minStock, maxStock))
            throw new RuntimeException("Value out of range value");
         */
    }

    public static int minValue() {
        return MIN_VALUE;
    }

    public static int maxValue() {
        return MAX_VALUE;
    }

    public static ArticleStock of(final Integer value) {
        return new ArticleStock(value);
    }

}
