package org.dalvarez.shop.core.article.infrastructure.rest_api.controller.post;

import org.dalvarez.shop.core.article.domain.Article;
import org.dalvarez.shop.core.article.infrastructure.rest_api.shared.request.ArticleBasicRequest;
import org.dalvarez.shop.shared.shared.infrastructure.validation.Field;
import org.dalvarez.shop.shared.shared.infrastructure.validation.FieldValidator;
import org.dalvarez.shop.shared.shared.infrastructure.validation.GenericNotEmptyValidator;
import org.dalvarez.shop.shared.shared.infrastructure.validation.InRangeValidator;

import java.util.List;
import java.util.Map;

public class ArticlePostRequest extends ArticleBasicRequest<ArticlePostRequest> {

    private static final Map<String, FieldValidator> fieldsValidators = Map.of(
            ArticleBasicRequest.FieldNames.STOCK, new InRangeValidator(Article.MIN_STOCK, Article.MAX_STOCK),
            ArticleBasicRequest.FieldNames.PRICE, new InRangeValidator(Article.MIN_PRICE, Article.MAX_PRICE),
            ArticleBasicRequest.FieldNames.NAME, GenericNotEmptyValidator.getInstance(),
            ArticleBasicRequest.FieldNames.DESCRIPTION, GenericNotEmptyValidator.getInstance()
    );

    public ArticlePostRequest(final Integer stock,
                              final Double price,
                              final String name,
                              final String description) {
        super(
                fieldsValidators,
                ArticlePostRequest.class,
                stock,
                price,
                name,
                description
        );
    }

    @Override
    protected List<Field<Object>> getFields() {
        return List.of(
                new Field<>(ArticleBasicRequest.FieldNames.STOCK, stock),
                new Field<>(ArticleBasicRequest.FieldNames.PRICE, price),
                new Field<>(ArticleBasicRequest.FieldNames.NAME, name),
                new Field<>(ArticleBasicRequest.FieldNames.DESCRIPTION, description)
        );
    }

}
