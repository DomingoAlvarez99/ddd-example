package org.dalvarez.ddd_example.article.application;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.Objects;

public class ArticleRequest {

    private String id;

    private Integer stock;

    private Double price;

    private String name;

    private String description;

    private String categoryId;

    private ArticleRequest() {
    }

    private ArticleRequest(final String id,
                           final Integer stock,
                           final Double price,
                           final String name,
                           final String description,
                           final String categoryId) {
        this.id = id;
        this.stock = stock;
        this.price = price;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
    }

    public static ArticleRequest of(final String id,
                                    final Integer stock,
                                    final Double price,
                                    final String name,
                                    final String description,
                                    final String categoryId) {
        return new ArticleRequest(
                id,
                stock,
                price,
                name,
                description,
                categoryId
        );
    }

    public static ArticleRequest of(final Integer stock,
                                    final Double price,
                                    final String name,
                                    final String description,
                                    final String categoryId) {
        return of(
                null,
                stock,
                price,
                name,
                description,
                categoryId
        );
    }

    @JsonGetter
    public String id() {
        return id;
    }

    @JsonGetter
    public Integer stock() {
        return stock;
    }

    @JsonGetter
    public Double price() {
        return price;
    }

    @JsonGetter
    public String name() {
        return name;
    }

    @JsonGetter
    public String description() {
        return description;
    }

    @JsonGetter
    public String categoryId() {
        return categoryId;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ArticleRequest that = (ArticleRequest) o;
        return (this == o) || (Objects.equals(id, that.id) && Objects.equals(stock, that.stock) && Objects.equals(
                price,
                that.price
        ) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(
                categoryId,
                that.categoryId
        ));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stock, price, name, description, categoryId);
    }

    @Override
    public String toString() {
        return "ArticleRequest{" +
                "id='" + id + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", categoryId='" + categoryId + '\'' +
                '}';
    }

}