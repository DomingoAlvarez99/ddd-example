package org.dalvarez.shop.shop_core.category.domain;

import java.util.Objects;

public class Category {

    protected final Long id;

    protected final String name;

    protected final String parentUuid;

    protected final String uuid;

    protected Category() {
        this.id = null;
        this.name = null;
        this.parentUuid = null;
        this.uuid = null;
    }

    protected Category(final Long id,
                       final String name,
                       final String parentUuid,
                       final String uuid) {
        this.id = id;
        this.name = name;
        this.parentUuid = parentUuid;
        this.uuid = uuid;
    }

    public static Category of(final Long id,
                              final String name,
                              final String parentUuid,
                              final String uuid) {
        return new Category(
                id,
                name,
                parentUuid,
                uuid
        );
    }

    public static Category of(final String name,
                              final String parentUuid,
                              final String uuid) {
        return of(
                null,
                name,
                parentUuid,
                uuid
        );
    }

    public static Category fromRequest(final Category request,
                                       final String uuid,
                                       final String parentUuid) {
        return new Category(
                null,
                request.getName(),
                parentUuid,
                uuid
        );
    }

    public static Category fromRequest(final Category request,
                                       final String parentUuid) {
        return fromRequest(request, request.getUuid(), parentUuid);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getParentUuid() {
        return parentUuid;
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
                ", parentUuid='" + parentUuid + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }

}
