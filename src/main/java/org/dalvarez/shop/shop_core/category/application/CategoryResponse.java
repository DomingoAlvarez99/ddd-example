package org.dalvarez.shop.shop_core.category.application;

import org.dalvarez.shop.shop_core.category.domain.model.Category;

public class CategoryResponse {

    private final String id;

    private final String name;

    private final String parentId;

    private CategoryResponse(final String id,
                             final String name,
                             final String parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public static CategoryResponse fromCategory(final Category category) {
        return new CategoryResponse(
                category.id()
                        .value(),
                category.name()
                        .value(),
                category.parentId()
                        .value()
        );
    }

    @Override
    public String toString() {
        return "CategoryResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                '}';
    }

}