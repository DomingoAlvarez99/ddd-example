package org.dalvarez.shop.shop_core.article_category.infrastructure.rest_api.controller.post;

import org.dalvarez.shop.shop_core.article_category.infrastructure.rest_api.shared.request.ArticleCategoryBasicRequest;

public class ArticleCategoryPostRequest extends ArticleCategoryBasicRequest<ArticleCategoryPostRequest> {

    public ArticleCategoryPostRequest(final String articleUuid,
                                      final String categoryUuid) {
        super(
                ArticleCategoryPostRequest.class,
                articleUuid,
                categoryUuid
        );
    }

}
