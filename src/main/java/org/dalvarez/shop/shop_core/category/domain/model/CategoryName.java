package org.dalvarez.shop.shop_core.category.domain.model;

import org.dalvarez.shop.shop_core.shop_common.shared.domain.value_object.StringValueObject;

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
