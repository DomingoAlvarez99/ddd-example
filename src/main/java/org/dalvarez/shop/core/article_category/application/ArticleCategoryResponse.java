package org.dalvarez.shop.core.article_category.application;

import org.dalvarez.shop.core.article.domain.Article;
import org.dalvarez.shop.core.article_category.domain.ArticleCategory;
import org.dalvarez.shop.core.category.domain.Category;

public class ArticleCategoryResponse extends ArticleCategory {

    protected ArticleCategoryResponse() {

    }

    public ArticleCategoryResponse(final Long id,
                                   final String uuid,
                                   final Article article,
                                   final Category category) {
        super(id, uuid, article, category);
    }

    public static ArticleCategoryResponse fromArticleCategory(final ArticleCategory articleCategory) {
        return new ArticleCategoryResponse(
                articleCategory.getId(),
                articleCategory.getUuid(),
                articleCategory.getArticle(),
                articleCategory.getCategory()
        );
    }

    public ArticleCategory toArticleCategory(final Long id) {
        return toArticleCategory(id, uuid);
    }

    public ArticleCategory toArticleCategory(final Long id,
                                             final String uuid) {
        return ArticleCategory.create(
                id,
                uuid,
                article,
                category
        );
    }

    public ArticleCategory toArticleCategory() {
        return toArticleCategory(id, uuid);
    }

    @Override
    public String toString() {
        return "ArticleCategoryResponse{" +
                "id=" + id +
                ", article=" + article +
                ", category=" + category +
                ", uuid='" + uuid + '\'' +
                '}';
    }

}