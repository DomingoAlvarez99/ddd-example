package org.dalvarez.shop.core.article.infrastructure.rest_api.controller.put;

import org.dalvarez.shop.core.article.infrastructure.rest_api.shared.request.ArticleRequest;
import org.dalvarez.shop.core.shared.domain.validation.Field;
import org.dalvarez.shop.core.shared.domain.validation.InvalidObjectException;

import java.util.List;

public class ArticlePutRequest extends ArticleRequest {

    public ArticlePutRequest(
            final String uuid,
            final Integer stock,
            final Double price,
            final String name,
            final String description) {
        super(
                null,
                uuid,
                stock,
                price,
                name,
                description
        );
    }

    @Override
    public void validate() throws InvalidObjectException {
        super.validate(getFields());
    }

    private List<Field<Object>> getFields() {
        return List.of(
                new Field<>(FieldNames.ID, id),
                new Field<>(FieldNames.UUID, uuid),
                new Field<>(FieldNames.STOCK, stock),
                new Field<>(FieldNames.PRICE, price),
                new Field<>(FieldNames.NAME, name),
                new Field<>(FieldNames.DESCRIPTION, description)
        );
    }

}
