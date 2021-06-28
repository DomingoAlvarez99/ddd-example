package org.dalvarez.shop.core.category.domain;

import java.util.Objects;

public final class Category {

    private final Long id;

    private final String name;

    private final Category parent;

    private final String uuid;

    private Category(final Long id,
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
