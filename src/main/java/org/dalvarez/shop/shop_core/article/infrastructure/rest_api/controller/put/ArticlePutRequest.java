package org.dalvarez.shop.shop_core.article.infrastructure.rest_api.controller.put;

import org.dalvarez.shop.shop_common.shared.infrastructure.validation.Field;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.UuidValidator;
import org.dalvarez.shop.shop_core.article.domain.Article;
import org.dalvarez.shop.shop_core.article.infrastructure.rest_api.shared.request.ArticleBasicRequest;

import java.util.List;

public class ArticlePutRequest extends ArticleBasicRequest<ArticlePutRequest> {

    public ArticlePutRequest(
            final Integer stock,
            final Double price,
            final String name,
            final String description) {
        super(
                ArticlePutRequest.class,
                stock,
                price,
                name,
                description
        );
    }

    @Override
    public Article validateAndGetRequest(final String uuid) {
        return super.validateAndGetRequest(uuid, customFieldValidators(uuid));
    }

    protected List<Field<Object>> customFieldValidators(final String uuid) {
        return List.of(new Field<>(ArticlePutRequest.FieldNames.UUID, uuid, UuidValidator.getInstance()));
    }

    public static class FieldNames {

        public static final String UUID = "uuid";

    }

}
