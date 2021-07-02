package org.dalvarez.shop.core.article.application;

import org.dalvarez.shop.core.article.domain.Article;

public class ArticleResponse extends Article {

    protected ArticleResponse() {

    }

    public ArticleResponse(final Long id,
                           final String uuid,
                           final Integer stock,
                           final Double price,
                           final String name,
                           final String description) {
        super(id, uuid, stock, price, name, description);
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