package org.dalvarez.shop.shop_core.article.infrastructure.hibernate_persistence;

import org.dalvarez.shop.shop_common.persistence.infrastructure.hibernate.BaseEntity;
import org.dalvarez.shop.shop_core.article.domain.Article;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = ArticleEntity.TABLE_NAME)
public final class ArticleEntity extends BaseEntity {

    static final String TABLE_NAME = "article";

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 600)
    private String description;

    public ArticleEntity() {

    }

    private ArticleEntity(final Long id,
                          final String uuid,
                          final Integer stock,
                          final Double price,
                          final String name,
                          final String description) {
        this.id = id;
        this.uuid = uuid;
        this.stock = stock;
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public Article toArticle() {
        return Article.of(
                id,
                uuid,
                stock,
                price,
                name,
                description
        );
    }

    public static ArticleEntity fromArticle(Article article) {
        return new ArticleEntity(
                article.getId(),
                article.getUuid(),
                article.getStock(),
                article.getPrice(),
                article.getName(),
                article.getDescription()
        );
    }

    @Override
    public String toString() {
        return "ArticleEntity{" +
                "id=" + id +
                ", stock=" + stock +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", uuid='" + uuid + '\'' +
                '}';
    }

    public static final class FieldNames {

        public static final String STOCK = "stock";

        public static final String PRICE = "price";

        public static final String NAME = "name";

        public static final String DESCRIPTION = "description";

    }

}
