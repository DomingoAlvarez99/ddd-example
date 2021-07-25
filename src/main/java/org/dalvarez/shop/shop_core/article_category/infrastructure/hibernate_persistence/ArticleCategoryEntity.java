package org.dalvarez.shop.shop_core.article_category.infrastructure.hibernate_persistence;

import org.dalvarez.shop.shop_common.persistence.infrastructure.hibernate.BaseEntity;
import org.dalvarez.shop.shop_core.article.infrastructure.hibernate_persistence.ArticleEntity;
import org.dalvarez.shop.shop_core.article_category.domain.ArticleCategory;
import org.dalvarez.shop.shop_core.category.infrastructure.hibernate_persistence.CategoryEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = ArticleCategoryEntity.TABLE_NAME)
public final class ArticleCategoryEntity extends BaseEntity {

    static final String TABLE_NAME = "article_category";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", referencedColumnName = "id")
    private ArticleEntity article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private CategoryEntity category;

    public ArticleCategoryEntity() {

    }

    private ArticleCategoryEntity(final Long id,
                                  final String uuid,
                                  final ArticleEntity article,
                                  final CategoryEntity category) {
        this.id = id;
        this.uuid = uuid;
        this.article = article;
        this.category = category;
    }

    public ArticleCategory toArticleCategory() {
        return ArticleCategory.create(
                id,
                uuid,
                article.toArticle(),
                category.toCategory()
        );
    }

    public static ArticleCategoryEntity fromArticleCategory(ArticleCategory articleCategory) {
        return new ArticleCategoryEntity(
                articleCategory.getId(),
                articleCategory.getUuid(),
                ArticleEntity.fromArticle(articleCategory.getArticle()),
                CategoryEntity.fromCategory(articleCategory.getCategory())
        );
    }


    public static final class FieldNames {

        public static final String ARTICLE = "article";

        public static final String CATEGORY = "category";

    }

}