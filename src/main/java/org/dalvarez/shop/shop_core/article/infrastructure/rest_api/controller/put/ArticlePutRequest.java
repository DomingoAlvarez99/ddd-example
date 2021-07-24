package org.dalvarez.shop.shop_core.article.infrastructure.rest_api.controller.put;

import org.dalvarez.shop.shop_core.article.domain.Article;
import org.dalvarez.shop.shop_core.article.infrastructure.rest_api.shared.request.ArticleBasicRequest;
import org.dalvarez.shop.shop_shared.shared.infrastructure.validation.Field;
import org.dalvarez.shop.shop_shared.shared.infrastructure.validation.FieldValidator;
import org.dalvarez.shop.shop_shared.shared.infrastructure.validation.GenericNotEmptyValidator;
import org.dalvarez.shop.shop_shared.shared.infrastructure.validation.IdValidator;
import org.dalvarez.shop.shop_shared.shared.infrastructure.validation.InRangeValidator;

import java.util.List;
import java.util.Map;

public class ArticlePutRequest extends ArticleBasicRequest<ArticlePutRequest> {

    private Long id;

    private static final Map<String, FieldValidator> fieldsValidators = Map.of(
            FieldNames.ID, IdValidator.getInstance(),
            ArticleBasicRequest.FieldNames.STOCK, new InRangeValidator(Article.MIN_STOCK, Article.MAX_STOCK),
            ArticleBasicRequest.FieldNames.PRICE, new InRangeValidator(Article.MIN_PRICE, Article.MAX_PRICE),
            ArticleBasicRequest.FieldNames.NAME, GenericNotEmptyValidator.getInstance(),
            ArticleBasicRequest.FieldNames.DESCRIPTION, GenericNotEmptyValidator.getInstance()
    );

    public ArticlePutRequest(
            final Integer stock,
            final Double price,
            final String name,
            final String description) {
        super(
                fieldsValidators,
                ArticlePutRequest.class,
                stock,
                price,
                name,
                description
        );
    }

    @Override
    public Article toArticle(final Long id) {
        this.id = id;

        return super.toArticle(id);
    }

    @Override
    protected List<Field<Object>> getFields() {
        return List.of(
                new Field<>(FieldNames.ID, id),
                new Field<>(ArticleBasicRequest.FieldNames.STOCK, stock),
                new Field<>(ArticleBasicRequest.FieldNames.PRICE, price),
                new Field<>(ArticleBasicRequest.FieldNames.NAME, name),
                new Field<>(ArticleBasicRequest.FieldNames.DESCRIPTION, description)
        );
    }

    @Override
    public String toString() {
        return "ArticlePutRequest{" +
                "id=" + id +
                ", stock=" + stock +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public static class FieldNames {

        public static final String ID = "id";

    }

}
