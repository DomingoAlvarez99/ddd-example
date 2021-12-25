package org.dalvarez.shop.shop_core.category.domain;

import java.util.Objects;

public class Category {

    protected final String name;

    protected final String parentUuid;

    protected final String uuid;

    protected Category() {
        this.name = null;
        this.parentUuid = null;
        this.uuid = null;
    }

    protected Category(final String name,
                       final String parentUuid,
                       final String uuid) {
        this.name = name;
        this.parentUuid = parentUuid;
        this.uuid = uuid;
    }

    public static Category of(final String name,
                              final String parentUuid,
                              final String uuid) {
        return new Category(
                name,
                parentUuid,
                uuid
        );
    }

    public static Category fromRequest(final Category request,
                                       final String uuid,
                                       final String parentUuid) {
        return new Category(
                request.getName(),
                parentUuid,
                uuid
        );
    }

    public static Category fromRequest(final Category request,
                                       final String parentUuid) {
        return fromRequest(request, request.getUuid(), parentUuid);
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

        return Objects.equals(uuid, category.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return "Category{" +
                ", name='" + name + '\'' +
                ", parentUuid='" + parentUuid + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }

}
