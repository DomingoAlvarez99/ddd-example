package org.dalvarez.shop.shop_core.category.application;

public class CategoryRequest {

    private final String id;

    private final String name;

    private final String parentId;

    private CategoryRequest(final String id,
                            final String name,
                            final String parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String parentId() {
        return parentId;
    }

    @Override
    public String toString() {
        return "CategoryRequest{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                '}';
    }

}