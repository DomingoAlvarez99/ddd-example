package org.dalvarez.shop.shop_core.article_category.infrastructure.rest_api.controller.put;

import org.dalvarez.shop.shop_common.shared.infrastructure.validation.Field;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.FieldValidator;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.UuidValidator;
import org.dalvarez.shop.shop_core.article_category.domain.ArticleCategory;
import org.dalvarez.shop.shop_core.article_category.infrastructure.rest_api.shared.request.ArticleCategoryBasicRequest;

import java.util.List;
import java.util.Map;

public class ArticleCategoryPutRequest extends ArticleCategoryBasicRequest<ArticleCategoryPutRequest> {

    private static final Map<String, FieldValidator> fieldsValidators = Map.of(
            FieldNames.UUID, UuidValidator.getInstance(),
            ArticleCategoryBasicRequest.FieldNames.ARTICLE_UUID, UuidValidator.getInstance(),
            ArticleCategoryBasicRequest.FieldNames.CATEGORY_UUID, UuidValidator.getInstance()
    );

    private String uuid;

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
    public ArticleCategory toArticleCategory(final String uuid) {
        this.uuid = uuid;

        return super.toArticleCategory(uuid);
    }

    @Override
    protected List<Field<Object>> getFields() {
        return List.of(
                new Field<>(ArticleCategoryPutRequest.FieldNames.UUID, uuid),
                new Field<>(ArticleCategoryBasicRequest.FieldNames.ARTICLE_UUID, articleUuid),
                new Field<>(ArticleCategoryBasicRequest.FieldNames.CATEGORY_UUID, categoryUuid)
        );
    }

    @Override
    public String toString() {
        return "ArticleCategoryPutRequest{" +
                "uuid='" + uuid + '\'' +
                ", articleUuid='" + articleUuid + '\'' +
                ", categoryUuid='" + categoryUuid + '\'' +
                '}';
    }

    public static class FieldNames {

        public static final String UUID = "uuid";

    }

}
