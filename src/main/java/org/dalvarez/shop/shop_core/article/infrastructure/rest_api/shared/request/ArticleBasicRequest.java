package org.dalvarez.shop.shop_core.article.infrastructure.rest_api.shared.request;

import org.dalvarez.shop.shop_core.article.domain.Article;
import org.dalvarez.shop.shop_shared.shared.infrastructure.validation.Field;
import org.dalvarez.shop.shop_shared.shared.infrastructure.validation.FieldValidator;
import org.dalvarez.shop.shop_shared.shared.infrastructure.validation.Validator;

import java.util.List;
import java.util.Map;

public abstract class ArticleBasicRequest<R> extends Validator<R> {

    protected Integer stock;

    protected Double price;

    protected String name;

    protected String description;

    private ArticleBasicRequest(final Map<String, FieldValidator> fieldsValidators,
                                final Class<R> requestClass) {
        super(fieldsValidators, requestClass);
    }

    protected ArticleBasicRequest(
            final Map<String, FieldValidator> fieldsValidators,
            final Class<R> requestClass,
            final Integer stock,
            final Double price,
            final String name,
            final String description) {
        this(fieldsValidators, requestClass);
        this.stock = stock;
        this.price = price;
        this.name = name;
        this.description = description;
    }

    @Override
    protected abstract List<Field<Object>> getFields();

    public Article toArticle() {
        return toArticle(null);
    }

    public Article toArticle(final Long id) {
        validate();

        return Article.create(
                id,
                null,
                stock,
                price,
                name,
                description
        );
    }

    public Integer getStock() {
        return stock;
    }

    public Double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ArticleBasicRequest{" +
                ", stock=" + stock +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public static class FieldNames {

        public static final String STOCK = "stock";

        public static final String PRICE = "price";

        public static final String NAME = "name";

        public static final String DESCRIPTION = "description";

    }

}
