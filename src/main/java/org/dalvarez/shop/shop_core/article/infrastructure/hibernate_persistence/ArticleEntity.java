package org.dalvarez.shop.shop_core.article.infrastructure.hibernate_persistence;

import org.dalvarez.shop.shop_core.article.domain.Article;
import org.dalvarez.shop.shop_shared.persistence.infrastructure.hibernate.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = ArticleEntity.TABLE_NAME)
public final class ArticleEntity extends BaseEntity {

    static final String TABLE_NAME = "article";

    private static final String SEQUENCE_NAME = "article_seq";

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
        return Article.create(
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

    public static final class FieldNames {

        public static final String STOCK = "stock";

        public static final String PRICE = "price";

        public static final String NAME = "name";

        public static final String DESCRIPTION = "description";

    }

}
