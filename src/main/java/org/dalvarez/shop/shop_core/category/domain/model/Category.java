package org.dalvarez.shop.shop_core.category.domain.model;

import org.dalvarez.shop.shop_core.shared.domain.category.CategoryId;

import java.util.Objects;

public class Category {

    private final CategoryId id;

    private CategoryName name;

    private CategoryId parentId;

    private Category() {
        this.id = null;
        this.name = null;
        this.parentId = null;
    }

    private Category(final CategoryId id,
                     final CategoryName name,
                     final CategoryId parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public static Category create(
            final CategoryName name,
            final CategoryId parentId) {
        return new Category(
                CategoryId.random(),
                name,
                parentId
        );
    }

    public void rename(final CategoryName name) {
        this.name = name;
    }

    public void updateParentId(final CategoryId parentId) {
        this.parentId = parentId;
    }

    public CategoryName name() {
        return name;
    }

    public CategoryId parentId() {
        return parentId;
    }

    public CategoryId id() {
        return id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        final Category category = (Category) o;

        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name=" + name +
                ", parentId=" + parentId +
                '}';
    }

}
