package org.dalvarez.shop.shop_core.category.infrastructure.rest_api.controller.post;

import org.dalvarez.shop.shop_core.category.application.CategoryResponse;
import org.dalvarez.shop.shop_core.category.application.create.CategoryCreator;
import org.dalvarez.shop.shop_core.category.infrastructure.rest_api.controller.CategoryApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public final class CategoryPostController extends CategoryApiController {

    private final CategoryCreator categoryCreator;

    public CategoryPostController(final CategoryCreator categoryCreator) {
        this.categoryCreator = categoryCreator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoryResponse> post(@RequestBody final CategoryPostRequest categoryPostRequest) {
        return new ResponseEntity<>(categoryCreator.create(categoryPostRequest.toCategory()), HttpStatus.CREATED);
    }

}
