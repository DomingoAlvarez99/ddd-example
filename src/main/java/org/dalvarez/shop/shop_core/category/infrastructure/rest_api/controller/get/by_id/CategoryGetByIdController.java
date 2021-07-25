package org.dalvarez.shop.shop_core.category.infrastructure.rest_api.controller.get.by_id;

import org.dalvarez.shop.shop_core.category.application.CategoryResponse;
import org.dalvarez.shop.shop_core.category.application.find.by_id.CategoryByIdFinder;
import org.dalvarez.shop.shop_core.category.infrastructure.rest_api.controller.CategoryApiController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class CategoryGetByIdController extends CategoryApiController {

    private final CategoryByIdFinder categoryByIdFinder;

    public CategoryGetByIdController(final CategoryByIdFinder categoryByIdFinder) {
        this.categoryByIdFinder = categoryByIdFinder;
    }

    @GetMapping(ID_PATH_VAR)
    public ResponseEntity<CategoryResponse> get(@PathVariable final Long id) {
        return ResponseEntity.ok(categoryByIdFinder.find(id));
    }

}
