package org.dalvarez.ddd_example.article.domain.model;

import org.dalvarez.ddd_example.shared.domain.value_object.StringValueObject;

public final class ArticleDescription extends StringValueObject {

    public static final int MIN_LENGTH = 5;

    public static final int MAX_LENGTH = 200;

    private ArticleDescription() {
        super(null);
    }

    private ArticleDescription(final String value) {
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

    public static ArticleDescription of(final String value) {
        return new ArticleDescription(value);
    }

}
