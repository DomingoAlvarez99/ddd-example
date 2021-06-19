package org.dalvarez.shop.core.article.infrastructure.rest_api.shared.request;

import org.dalvarez.shop.core.article.domain.Article;
import org.dalvarez.shop.core.shared.domain.exception.BadRequestException;
import org.dalvarez.shop.core.shared.domain.validation.FieldValidator;
import org.dalvarez.shop.core.shared.domain.validation.GenericNotEmptyValidator;
import org.dalvarez.shop.core.shared.domain.validation.IdValidator;
import org.dalvarez.shop.core.shared.domain.validation.InRangeValidator;
import org.dalvarez.shop.core.shared.domain.validation.InvalidObjectException;
import org.dalvarez.shop.core.shared.domain.validation.UuidValidator;
import org.dalvarez.shop.core.shared.domain.validation.Validator;

import java.util.Map;

public abstract class ArticleRequest extends Validator<ArticleRequest> {

    protected Long id;

    protected String uuid;

    protected Integer stock;

    protected Double price;

    protected String name;

    protected String description;

    protected static final Map<String, FieldValidator> fieldsValidators = Map.of(
            FieldNames.ID, IdValidator.getInstance(),
            FieldNames.UUID, UuidValidator.getInstance(),
            FieldNames.STOCK, new InRangeValidator(Article.MIN_STOCK, Article.MAX_STOCK),
            FieldNames.PRICE, new InRangeValidator(Article.MIN_PRICE, Article.MAX_PRICE),
            FieldNames.NAME, GenericNotEmptyValidator.getInstance(),
            FieldNames.DESCRIPTION, GenericNotEmptyValidator.getInstance()
    );

    public ArticleRequest() {
        super(fieldsValidators, ArticleRequest.class);
    }

    protected ArticleRequest(final Long id,
                             final String uuid,
                             final Integer stock,
                             final Double price,
                             final String name,
                             final String description) {
        this();
        this.id = id;
        this.uuid = uuid;
        this.stock = stock;
        this.price = price;
        this.name = name;
        this.description = description;
    }

    protected abstract void validate() throws InvalidObjectException;

    public Article toArticle() {
        return toArticle(id);
    }

    public Article toArticle(Long id) {
        this.id = id;
        try {
            validate();
        } catch (InvalidObjectException e) {
            throw new BadRequestException(e);
        }

        return Article.create(
                id,
                uuid,
                stock,
                price,
                name,
                description
        );
    }

    protected Long getId() {
        return id;
    }

    protected String getUuid() {
        return uuid;
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

    public static class FieldNames {

        public static final String ID = "id";

        public static final String UUID = "uuid";

        public static final String STOCK = "stock";

        public static final String PRICE = "price";

        public static final String NAME = "name";

        public static final String DESCRIPTION = "description";

    }

}
