package org.dalvarez.shop.shop_core.category.infrastructure.hibernate_persistence;

import org.dalvarez.shop.shop_core.category.domain.Category;
import org.dalvarez.shop.shop_common.persistence.infrastructure.hibernate.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = CategoryEntity.TABLE_NAME)
public final class CategoryEntity extends BaseEntity {

    static final String TABLE_NAME = "category";

    private static final String SEQUENCE_NAME = "category_seq";

    private static final int SEQUENCE_ALLOCATION_SIZE = 1;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = SEQUENCE_NAME
    )
    @SequenceGenerator(
            name = SEQUENCE_NAME,
            allocationSize = SEQUENCE_ALLOCATION_SIZE
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private CategoryEntity parent;

    @Column(nullable = false, length = 100)
    private String name;

    public CategoryEntity() {

    }

    private CategoryEntity(final Long id,
                           final String uuid,
                           final String name,
                           final CategoryEntity parent) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.parent = parent;
    }

    public Category toCategory() {
        return Category.create(
                id,
                name,
                parent != null ? parent.toCategory() : null,
                uuid
        );
    }

    public static CategoryEntity fromCategory(Category category) {
        return new CategoryEntity(
                category.getId(),
                category.getUuid(),
                category.getName(),
                category.getParent() != null ? CategoryEntity.fromCategory(category.getParent()) : null
        );
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "id=" + id +
                ", parent=" + parent +
                ", name='" + name + '\'' +
                '}';
    }

    public static final class FieldNames {

        public static final String NAME = "name";

        public static final String PARENT = "parent";

    }

}
