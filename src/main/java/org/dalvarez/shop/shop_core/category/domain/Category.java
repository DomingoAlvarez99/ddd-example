package org.dalvarez.shop.shop_core.category.domain;

import java.util.Objects;

public class Category {

    protected final Long id;

    protected final String name;

    protected final Category parent;

    protected final String uuid;

    protected Category() {
        this.id = null;
        this.name = null;
        this.parent = null;
        this.uuid = null;
    }

    protected Category(final Long id,
                       final String name,
                       final Category parent,
                       final String uuid) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.uuid = uuid;
    }

    public static Category create(final Long id,
                                  final String name,
                                  final Category parent,
                                  final String uuid) {
        return new Category(
                id,
                name,
                parent,
                uuid
        );
    }

    public static Category create(final String name,
                                  final Category parent,
                                  final String uuid) {
        return create(
                null,
                name,
                parent,
                uuid
        );
    }

    public static Category fromRequest(final Category request,
                                       final String uuid,
                                       final Category parent) {
        return new Category(
                null,
                request.getName(),
                parent,
                uuid
        );
    }

    public static Category fromRequest(final Category request,
                                       final Category parent) {
        return fromRequest(request, request.getUuid(), parent);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getParent() {
        return parent;
    }

    public String getUuid() {
        return uuid;
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
                ", name='" + name + '\'' +
                ", parent=" + parent +
                ", uuid='" + uuid + '\'' +
                '}';
    }

}
