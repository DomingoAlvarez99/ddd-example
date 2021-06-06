package org.dalvarez.shop.core.article.application;

import org.dalvarez.shop.core.article.domain.Article;

import java.util.Objects;

public class ArticleResponse {

    private Long id;

    private String uuid;

    private Integer stock;

    private Double price;

    private String name;

    private String description;

    public ArticleResponse() {

    }

    public ArticleResponse(final Long id,
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

    public Article toArticle(final Long id) {
        return Article.create(
                id,
                uuid,
                stock,
                price,
                name,
                description
        );
    }

    public Article toArticle(final Long id,
                             final String uuid) {
        return Article.create(
                id,
                uuid,
                stock,
                price,
                name,
                description
        );
    }

    public Article toArticle() {
        return toArticle(id, uuid);
    }

    public static ArticleResponse fromArticle(final Article article) {
        return new ArticleResponse(
                article.getId(),
                article.getUuid(),
                article.getStock(),
                article.getPrice(),
                article.getName(),
                article.getDescription()
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

        final ArticleResponse response = (ArticleResponse) o;

        return Objects.equals(uuid, response.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return "ArticleResponse{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}