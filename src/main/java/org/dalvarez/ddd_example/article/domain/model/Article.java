package org.dalvarez.ddd_example.article.domain.model;

import org.dalvarez.ddd_example.shared.domain.category.CategoryId;

import java.util.Objects;

public class Article {

    private final ArticleId id;

    private final ArticleStock stock;

    private final ArticlePrice price;

    private ArticleName name;

    private final ArticleDescription description;

    private CategoryId categoryId;

    private Article() {
        id = null;
        stock = null;
        price = null;
        description = null;
    }

    private Article(final ArticleId id,
                    final ArticleStock stock,
                    final ArticlePrice price,
                    final ArticleName name,
                    final ArticleDescription description,
                    final CategoryId categoryId) {
        this.id = id;
        this.stock = stock;
        this.price = price;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
    }

    public static Article of(final ArticleId id,
                             final ArticleStock stock,
                             final ArticlePrice price,
                             final ArticleName name,
                             final ArticleDescription description,
                             final CategoryId categoryId) {
        return new Article(
                id,
                stock,
                price,
                name,
                description,
                categoryId
        );
    }

    public ArticleId id() {
        return id;
    }

    public ArticleStock stock() {
        return stock;
    }

    public ArticlePrice price() {
        return price;
    }

    public ArticleName name() {
        return name;
    }

    public ArticleDescription description() {
        return description;
    }

    public CategoryId categoryId() {
        return categoryId;
    }

    public void rename(ArticleName articleName) {
        this.name = articleName;
    }

    public void updateCategory(CategoryId categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Article article = (Article) o;
        return Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", stock=" + stock +
                ", price=" + price +
                ", name=" + name +
                ", description=" + description +
                ", categoryId=" + categoryId +
                '}';
    }

    public static final class FieldNames {

        public static final String ID = "id";

        public static final String STOCK = "stock";

        public static final String PRICE = "price";

        public static final String NAME = "name";

        public static final String DESCRIPTION = "description";

        public static final String CATEGORY_ID = "categoryId";

    }

}
