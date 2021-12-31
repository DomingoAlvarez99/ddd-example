package org.dalvarez.shop.shop_core.article.domain.model;

import org.dalvarez.shop.shop_common.shared.domain.value_object.StringValueObject;

public final class ArticleName extends StringValueObject {

    public static final int MIN_LENGTH = 5;

    public static final int MAX_LENGTH = 200;

    public ArticleName() {
        super(null);
    }

    private ArticleName(final String value) {
        super(value);
        ensureIsValidValue();
    }

    private void ensureIsValidValue() {
        ensureHasValue();
        ensureReachMinimumLength();
        ensureNotExceedMaximumLength();
    }

    private void ensureHasValue() {
        if (isNullOrBlank())
            InvalidArticleNameException.throwCauseOfIsBlank();
    }

    private void ensureReachMinimumLength() {
        if (hasLessLength(MIN_LENGTH))
            InvalidArticleNameException.throwCauseOfNotReachMinimumLength(value(), MIN_LENGTH);
    }

    private void ensureNotExceedMaximumLength() {
        if (hasMoreLength(MAX_LENGTH))
            InvalidArticleNameException.throwCauseOfExceedMaximumLength(value(), MAX_LENGTH);
    }

    public static ArticleName of(final String value) {
        return new ArticleName(value);
    }

}
