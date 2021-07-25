package org.dalvarez.shop.shop_core.article_category.infrastructure.rest_api.controller.post;

import org.dalvarez.shop.shop_core.article_category.infrastructure.rest_api.shared.request.ArticleCategoryBasicRequest;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.Field;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.FieldValidator;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.UuidValidator;

import java.util.List;
import java.util.Map;

public class ArticleCategoryPostRequest extends ArticleCategoryBasicRequest<ArticleCategoryPostRequest> {

    private static final Map<String, FieldValidator> fieldsValidators = Map.of(
            FieldNames.ARTICLE_UUID, UuidValidator.getInstance(),
            FieldNames.CATEGORY_UUID, UuidValidator.getInstance()
    );

    public ArticleCategoryPostRequest(final String articleUuid,
                                      final String categoryUuid) {
        super(
                fieldsValidators,
                ArticleCategoryPostRequest.class,
                articleUuid,
                categoryUuid
        );
    }

    @Override
    protected List<Field<Object>> getFields() {
        return List.of(
                new Field<>(FieldNames.ARTICLE_UUID, articleUuid),
                new Field<>(FieldNames.CATEGORY_UUID, categoryUuid)
        );
    }

}
