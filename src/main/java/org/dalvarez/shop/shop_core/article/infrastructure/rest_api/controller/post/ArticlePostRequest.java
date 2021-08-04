package org.dalvarez.shop.shop_core.article.infrastructure.rest_api.controller.post;

import org.dalvarez.shop.shop_core.article.infrastructure.rest_api.shared.request.ArticleBasicRequest;

public class ArticlePostRequest extends ArticleBasicRequest<ArticlePostRequest> {

    public ArticlePostRequest(final Integer stock,
                              final Double price,
                              final String name,
                              final String description) {
        super(
                ArticlePostRequest.class,
                stock,
                price,
                name,
                description
        );
    }

}
