package org.dalvarez.shop.shop_core.article_category.infrastructure.rest_api.shared.request;

import org.dalvarez.shop.shop_core.article.domain.Article;
import org.dalvarez.shop.shop_core.article_category.domain.ArticleCategory;
import org.dalvarez.shop.shop_core.category.domain.Category;
import org.dalvarez.shop.shop_shared.shared.infrastructure.validation.Field;
import org.dalvarez.shop.shop_shared.shared.infrastructure.validation.FieldValidator;
import org.dalvarez.shop.shop_shared.shared.infrastructure.validation.Validator;

import java.util.List;
import java.util.Map;

public abstract class ArticleCategoryBasicRequest<R> extends Validator<R> {

    protected String articleUuid;

    protected String categoryUuid;

    private ArticleCategoryBasicRequest(final Map<String, FieldValidator> fieldsValidators,
                                        final Class<R> requestClass) {
        super(fieldsValidators, requestClass);
    }

    protected ArticleCategoryBasicRequest(
            final Map<String, FieldValidator> fieldsValidators,
            final Class<R> requestClass,
            final String articleUuid,
            final String categoryUuid) {
        this(fieldsValidators, requestClass);
        this.articleUuid = articleUuid;
        this.categoryUuid = categoryUuid;
    }

    @Override
    protected abstract List<Field<Object>> getFields();

    public ArticleCategory toArticleCategory() {
        return toArticleCategory(null);
    }

    public ArticleCategory toArticleCategory(final Long id) {
        validate();

        return ArticleCategory.create(
                id,
                null,
                Article.create(null, articleUuid, null, null, null, null),
                Category.create(null, null, null, categoryUuid)
        );
    }

    public String getArticleUuid() {
        return articleUuid;
    }

    public String getCategoryUuid() {
        return categoryUuid;
    }

    @Override
    public String toString() {
        return "ArticleCategoryBasicRequest{" +
                "articleUuid='" + articleUuid + '\'' +
                ", categoryUuid='" + categoryUuid + '\'' +
                '}';
    }

    public static class FieldNames {

        public static final String ARTICLE_UUID = "articleUuid";

        public static final String CATEGORY_UUID = "categoryUuid";

    }

}
