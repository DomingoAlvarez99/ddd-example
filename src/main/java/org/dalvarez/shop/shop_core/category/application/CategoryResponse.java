package org.dalvarez.shop.shop_core.category.application;

import org.dalvarez.shop.shop_core.category.domain.Category;

public class CategoryResponse extends Category {

    protected CategoryResponse() {

    }

    public CategoryResponse(final Long id,
                            final String name,
                            final String parentUuid,
                            final String uuid) {
        super(id, name, parentUuid, uuid);
    }

    public static CategoryResponse fromCategory(final Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getParentUuid(),
                category.getUuid()
        );
    }

    public Category toCategory(final Long id) {
        return toCategory(id, uuid);
    }

    public Category toCategory(final Long id,
                               final String uuid) {
        return Category.of(
                id,
                name,
                parentUuid,
                uuid
        );
    }

    public Category toCategory() {
        return toCategory(id, uuid);
    }

    @Override
    public String toString() {
        return "CategoryResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentUuid='" + parentUuid + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }

}