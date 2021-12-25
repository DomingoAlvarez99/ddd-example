package org.dalvarez.shop.shop_core.category.application;

import org.dalvarez.shop.shop_core.category.domain.Category;

public class CategoryResponse extends Category {

    protected CategoryResponse() {

    }

    public CategoryResponse(final String name,
                            final String parentUuid,
                            final String uuid) {
        super(name, parentUuid, uuid);
    }

    public static CategoryResponse fromCategory(final Category category) {
        return new CategoryResponse(
                category.getName(),
                category.getParentUuid(),
                category.getUuid()
        );
    }

    public Category toCategory() {
        return toCategory(uuid);
    }

    public Category toCategory(final String uuid) {
        return Category.of(
                name,
                parentUuid,
                uuid
        );
    }

    @Override
    public String toString() {
        return "CategoryResponse{" +
                ", name='" + name + '\'' +
                ", parentUuid='" + parentUuid + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }

}