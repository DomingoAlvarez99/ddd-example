package org.dalvarez.shop.core.article_category.domain;

import org.dalvarez.shop.core.article.domain.Article;
import org.dalvarez.shop.core.category.domain.Category;

import java.util.Objects;

public class ArticleCategory {

    protected final Long id;

    protected final Article article;

    protected final Category category;

    protected final String uuid;

    public ArticleCategory() {
        this.id = null;
        this.article = null;
        this.category = null;
        this.uuid = null;
    }

    protected ArticleCategory(final Long id,
                              final String uuid,
                              final Article article,
                              final Category category) {
        this.id = id;
        this.uuid = uuid;
        this.article = article;
        this.category = category;
    }

    public static ArticleCategory create(final Long id,
                                         final String uuid,
                                         final Article article,
                                         final Category category) {
        return new ArticleCategory(
                id,
                uuid,
                article,
                category
        );
    }

    public static ArticleCategory create(final String uuid,
                                         final Article article,
                                         final Category category) {
        return create(
                null,
                uuid,
                article,
                category
        );
    }

    public static ArticleCategory fromRequest(final String uuid,
                                              final Category category,
                                              final Article article) {
        return new ArticleCategory(
                null,
                uuid,
                article,
                category
        );
    }

    public Long getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public Article getArticle() {
        return article;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        final ArticleCategory category = (ArticleCategory) o;

        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ArticleCategory{" +
                "id=" + id +
                ", article=" + article +
                ", category=" + category +
                ", uuid='" + uuid + '\'' +
                '}';
    }

}
