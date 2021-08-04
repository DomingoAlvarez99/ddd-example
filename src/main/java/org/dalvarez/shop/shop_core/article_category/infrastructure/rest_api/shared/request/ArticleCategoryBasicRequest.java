package org.dalvarez.shop.shop_core.article_category.infrastructure.rest_api.shared.request;

import org.dalvarez.shop.shop_common.shared.domain.util.CollectionUtils;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.Field;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.RequestValidator;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.UuidValidator;
import org.dalvarez.shop.shop_core.article.domain.Article;
import org.dalvarez.shop.shop_core.article_category.domain.ArticleCategory;
import org.dalvarez.shop.shop_core.category.domain.Category;

import java.util.Collections;
import java.util.List;

public abstract class ArticleCategoryBasicRequest<R> extends RequestValidator<R> {

    protected String articleUuid;

    protected String categoryUuid;

    private ArticleCategoryBasicRequest(final Class<R> requestClass) {
        super(requestClass);
    }

    protected ArticleCategoryBasicRequest(
            final Class<R> requestClass,
            final String articleUuid,
            final String categoryUuid) {
        this(requestClass);

        this.articleUuid = articleUuid;
        this.categoryUuid = categoryUuid;
    }

    private ArticleCategory toArticleCategory(final String uuid) {
        return ArticleCategory.create(
                uuid,
                Article.of(null, articleUuid, null, null, null, null),
                Category.of(null, null, null, categoryUuid)
        );
    }

    public ArticleCategory validateAndGetRequest() {
        return validateAndGetRequest(null);
    }

    public ArticleCategory validateAndGetRequest(final String uuid) {
        return validateAndGetRequest(uuid, Collections.emptyList());
    }

    protected ArticleCategory validateAndGetRequest(final String uuid,
                                                    final List<Field<Object>> customFieldValidators) {
        validate(CollectionUtils.concat(getDefaultFieldValidators(), customFieldValidators));

        return toArticleCategory(uuid);
    }

    protected List<Field<Object>> getDefaultFieldValidators() {
        return List.of(
                new Field<>(FieldNames.ARTICLE_UUID, articleUuid, UuidValidator.getInstance()),
                new Field<>(FieldNames.CATEGORY_UUID, categoryUuid, UuidValidator.getInstance())
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
