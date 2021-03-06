package org.dalvarez.ddd_example.shared.domain.category;

import org.dalvarez.ddd_example.shared.domain.value_object.id.Identifier;

public final class CategoryId extends Identifier {

    private CategoryId() {
        super();
    }

    private CategoryId(final String value) {
        super(value);
    }

    public static CategoryId random() {
        return new CategoryId();
    }

    public static CategoryId of(final String value) {
        return new CategoryId(value);
    }

}
