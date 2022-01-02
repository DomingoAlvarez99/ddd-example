package org.dalvarez.ddd_example.article.application;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.dalvarez.ddd_example.article.domain.model.Article;

import java.util.Objects;

public class ArticleResponse {

    private String id;

    private Integer stock;

    private Double price;

    private String name;

    private String description;

    private String categoryId;

    public ArticleResponse() {
    }

    public ArticleResponse(final String id,
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

    public void setId(final String id) {
        this.id = id;
    }

    public void setStock(final Integer stock) {
        this.stock = stock;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setCategoryId(final String categoryId) {
        this.categoryId = categoryId;
    }

    public static ArticleResponse fromArticle(final Article article) {
        return new ArticleResponse(
                article.id()
                       .value(),
                article.stock()
                       .value(),
                article.price()
                       .value(),
                article.name()
                       .value(),
                article.description()
                       .value(),
                article.categoryId() == null ? null : article.categoryId()
                                                             .value()
        );
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ArticleResponse that = (ArticleResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(stock, that.stock) && Objects.equals(
                price,
                that.price
        ) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(
                categoryId,
                that.categoryId
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stock, price, name, description, categoryId);
    }

    @Override
    public String toString() {
        return "ArticleResponse{" +
                "id='" + id + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", categoryId='" + categoryId + '\'' +
                '}';
    }

}