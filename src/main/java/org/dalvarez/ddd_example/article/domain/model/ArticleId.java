package org.dalvarez.ddd_example.article.domain.model;

import org.dalvarez.ddd_example.shared.domain.value_object.id.Identifier;

public final class ArticleId extends Identifier {

    private ArticleId() {

    }

    private ArticleId(final String value) {
        super(value);
    }

    public static ArticleId random() {
        return new ArticleId();
    }

    public static ArticleId of(final String value) {
        return new ArticleId(value);
    }

}
