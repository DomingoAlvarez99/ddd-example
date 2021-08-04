package org.dalvarez.shop.shop_core.article_category.infrastructure.rest_api.controller.put;

import org.dalvarez.shop.shop_common.shared.infrastructure.validation.Field;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.UuidValidator;
import org.dalvarez.shop.shop_core.article_category.domain.ArticleCategory;
import org.dalvarez.shop.shop_core.article_category.infrastructure.rest_api.shared.request.ArticleCategoryBasicRequest;
import org.dalvarez.shop.shop_core.category.infrastructure.rest_api.controller.put.CategoryPutRequest;

import java.util.List;

public class ArticleCategoryPutRequest extends ArticleCategoryBasicRequest<ArticleCategoryPutRequest> {

    public ArticleCategoryPutRequest(final String articleUuid,
                                     final String categoryUuid) {
        super(
                ArticleCategoryPutRequest.class,
                articleUuid,
                categoryUuid
        );
    }

    @Override
    public ArticleCategory validateAndGetRequest(final String uuid) {
        return super.validateAndGetRequest(uuid, customFieldValidators(uuid));
    }

    protected List<Field<Object>> customFieldValidators(final String uuid) {
        return List.of(new Field<>(CategoryPutRequest.FieldNames.UUID, uuid, UuidValidator.getInstance()));
    }

    public static class FieldNames {

        public static final String UUID = "uuid";

    }

}
