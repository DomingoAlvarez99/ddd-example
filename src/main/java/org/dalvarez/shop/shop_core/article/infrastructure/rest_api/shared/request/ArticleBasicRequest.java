package org.dalvarez.shop.shop_core.article.infrastructure.rest_api.shared.request;

import org.dalvarez.shop.shop_common.shared.domain.util.CollectionUtils;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.Field;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.GenericNotEmptyValidator;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.InRangeValidator;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.RequestValidator;
import org.dalvarez.shop.shop_core.article.domain.Article;

import java.util.Collections;
import java.util.List;

public abstract class ArticleBasicRequest<R> extends RequestValidator<R> {

    protected Integer stock;

    protected Double price;

    protected String name;

    protected String description;

    private ArticleBasicRequest(final Class<R> requestClass) {
        super(requestClass);
    }

    protected ArticleBasicRequest(
            final Class<R> requestClass,
            final Integer stock,
            final Double price,
            final String name,
            final String description) {
        this(requestClass);
        this.stock = stock;
        this.price = price;
        this.name = name;
        this.description = description;
    }

    private Article toArticle(final String uuid) {
        return Article.of(
                null,
                uuid,
                stock,
                price,
                name,
                description
        );
    }

    public Article validateAndGetRequest() {
        return validateAndGetRequest(null);
    }

    public Article validateAndGetRequest(final String uuid) {
        return validateAndGetRequest(uuid, Collections.emptyList());
    }

    protected Article validateAndGetRequest(final String uuid,
                                            final List<Field<Object>> customFieldValidators) {
        validate(CollectionUtils.concat(getDefaultFieldValidators(), customFieldValidators));

        return toArticle(uuid);
    }

    protected List<Field<Object>> getDefaultFieldValidators() {
        return List.of(
                new Field<>(
                        ArticleBasicRequest.FieldNames.STOCK,
                        stock,
                        new InRangeValidator(Article.MIN_STOCK, Article.MAX_STOCK)
                ),
                new Field<>(
                        ArticleBasicRequest.FieldNames.PRICE,
                        price,
                        new InRangeValidator(Article.MIN_PRICE, Article.MAX_PRICE)
                ),
                new Field<>(ArticleBasicRequest.FieldNames.NAME, name, GenericNotEmptyValidator.getInstance()),
                new Field<>(
                        ArticleBasicRequest.FieldNames.DESCRIPTION,
                        description,
                        GenericNotEmptyValidator.getInstance()
                )
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
