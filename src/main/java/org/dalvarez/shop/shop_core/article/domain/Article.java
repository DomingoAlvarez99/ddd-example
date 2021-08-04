package org.dalvarez.shop.shop_core.article.domain;

import java.util.Objects;

public class Article {

    public static final Integer MIN_STOCK = 1;

    public static final Integer MAX_STOCK = 100;

    public static final Double MIN_PRICE = 0D;

    public static final Double MAX_PRICE = 1000D;

    private final Long id;

    private final String uuid;

    private final Integer stock;

    private final Double price;

    private final String name;

    private final String description;

    private Article(final Long id,
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

    public static Article of(final Long id,
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
        return fromRequest(request, uuid, null);
    }

    public static Article fromRequest(final Article request,
                                      final String uuid,
                                      final Long id) {
        return new Article(
                id,
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Article article = (Article) o;
        return Objects.equals(uuid, article.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
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
