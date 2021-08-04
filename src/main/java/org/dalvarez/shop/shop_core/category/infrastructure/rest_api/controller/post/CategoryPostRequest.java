package org.dalvarez.shop.shop_core.category.infrastructure.rest_api.controller.post;

import org.dalvarez.shop.shop_core.category.infrastructure.rest_api.shared.CategoryBasicRequest;

public class CategoryPostRequest extends CategoryBasicRequest<CategoryPostRequest> {

    public CategoryPostRequest(final String name,
                               final String parentUuid) {
        super(
                CategoryPostRequest.class,
                name,
                parentUuid
        );
    }

}
