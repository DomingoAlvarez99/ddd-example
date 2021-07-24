package org.dalvarez.shop.shop_core.article_category.infrastructure.rest_api.controller.put;

import org.dalvarez.shop.shop_core.article_category.domain.ArticleCategory;
import org.dalvarez.shop.shop_core.article_category.infrastructure.rest_api.shared.request.ArticleCategoryBasicRequest;
import org.dalvarez.shop.shop_core.category.infrastructure.rest_api.controller.put.CategoryPutRequest;
import org.dalvarez.shop.shop_shared.shared.infrastructure.validation.Field;
import org.dalvarez.shop.shop_shared.shared.infrastructure.validation.FieldValidator;
import org.dalvarez.shop.shop_shared.shared.infrastructure.validation.IdValidator;
import org.dalvarez.shop.shop_shared.shared.infrastructure.validation.UuidValidator;

import java.util.List;
import java.util.Map;

public class ArticleCategoryPutRequest extends ArticleCategoryBasicRequest<ArticleCategoryPutRequest> {

    private static final Map<String, FieldValidator> fieldsValidators = Map.of(
            FieldNames.ID, IdValidator.getInstance(),
            ArticleCategoryBasicRequest.FieldNames.ARTICLE_UUID, UuidValidator.getInstance(),
            ArticleCategoryBasicRequest.FieldNames.CATEGORY_UUID, UuidValidator.getInstance()
    );

    private Long id;

    public ArticleCategoryPutRequest(final String articleUuid,
                                     final String categoryUuid) {
        super(
                fieldsValidators,
                ArticleCategoryPutRequest.class,
                articleUuid,
                categoryUuid
        );
    }

    @Override
    public ArticleCategory toArticleCategory(final Long id) {
        this.id = id;

        return super.toArticleCategory(id);
    }

    @Override
    protected List<Field<Object>> getFields() {
        return List.of(
                new Field<>(CategoryPutRequest.FieldNames.ID, id),
                new Field<>(ArticleCategoryBasicRequest.FieldNames.ARTICLE_UUID, articleUuid),
                new Field<>(ArticleCategoryBasicRequest.FieldNames.CATEGORY_UUID, categoryUuid)
        );
    }

    @Override
    public String toString() {
        return "ArticleCategoryPuttRequest{" +
                "id=" + id +
                ", articleUuid='" + articleUuid + '\'' +
                ", categoryUuid='" + categoryUuid + '\'' +
                '}';
    }

    public static class FieldNames {

        public static final String ID = "id";

    }

}
