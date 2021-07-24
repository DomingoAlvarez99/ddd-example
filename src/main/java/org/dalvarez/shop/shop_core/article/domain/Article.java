package org.dalvarez.shop.shop_core.article.domain;

import java.util.Objects;

public class Article {

    public static final Integer MIN_STOCK = 1;

    public static final Integer MAX_STOCK = 100;

    public static final Double MIN_PRICE = 0D;

    public static final Double MAX_PRICE = 1000D;

    protected final Long id;

    protected final String uuid;

    protected final Integer stock;

    protected final Double price;

    protected final String name;

    protected final String description;

    protected Article() {
        this.id = null;
        this.uuid = null;
        this.stock = null;
        this.price = null;
        this.name = null;
        this.description = null;
    }

    protected Article(final Long id,
                      final String uuid,
                      final Integer stock,
                      final Double price,
                      final String name,
                      final String description) {
        this.id = id;
        this.uuid = uuid;
        this.stock = stock;
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public static Article create(final Long id,
                                 final String uuid,
                                 final Integer stock,
                                 final Double price,
                                 final String name,
                                 final String description) {
        return new Article(
                id,
                uuid,
                stock,
                price,
                name,
                description
        );
    }

    public static Article fromRequest(final Article request,
                                      final String uuid) {
        return new Article(
                null,
                uuid,
                request.getStock(),
                request.getPrice(),
                request.getName(),
                request.getDescription()
        );
    }

    public Long getId() {
        return id;
    }

    public String getUuid() {
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

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Article article = (Article) o;

        return Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
