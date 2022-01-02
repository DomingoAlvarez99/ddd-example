package org.dalvarez.ddd_example.category.domain.model;

import org.dalvarez.ddd_example.shared.domain.value_object.StringValueObject;

public final class CategoryName extends StringValueObject {

    public CategoryName() {
        super(null);
    }

    private CategoryName(final String value) {
        super(value);
    }

    public static CategoryName of(final String value) {
        return new CategoryName(value);
    }

}
