package org.dalvarez.ddd_example.category.infrastructure.rest_api.controller.post;

import org.dalvarez.ddd_example.category.application.CategoryRequest;
import org.dalvarez.ddd_example.category.application.CategoryResponse;
import org.dalvarez.ddd_example.category.application.create.CategoryCreator;
import org.dalvarez.ddd_example.category.infrastructure.rest_api.controller.CategoryApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class CategoryPostController extends CategoryApiController {

    private final CategoryCreator categoryCreator;

    public CategoryPostController(final CategoryCreator categoryCreator) {
        this.categoryCreator = categoryCreator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoryResponse> post(@RequestBody final CategoryRequest categoryRequest) {
        return new ResponseEntity<>(categoryCreator.create(categoryRequest), HttpStatus.CREATED);
    }

}
