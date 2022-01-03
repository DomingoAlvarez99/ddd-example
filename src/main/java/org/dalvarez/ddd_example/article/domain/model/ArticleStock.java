package org.dalvarez.ddd_example.article.domain.model;

import org.dalvarez.ddd_example.shared.domain.value_object.IntValueObject;

import java.util.Objects;

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
        if (Objects.isNull(value()))
            InvalidArticlePriceException.throwCauseOfIsNull();

        if (!isBetweenExclusive(MIN_VALUE, MAX_VALUE))
            InvalidArticlePriceException.throwCauseOfIsNotBetweenExpectedValues(value(), MIN_VALUE, MAX_VALUE);
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
