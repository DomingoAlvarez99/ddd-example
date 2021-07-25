package org.dalvarez.shop.shop_core.category.infrastructure.rest_api.controller.get.by_uuid;

import org.dalvarez.shop.shop_core.category.application.CategoryResponse;
import org.dalvarez.shop.shop_core.category.application.find.by_uuid.CategoryByUuidFinder;
import org.dalvarez.shop.shop_core.category.infrastructure.rest_api.controller.CategoryApiController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class CategoryGetByUuidController extends CategoryApiController {

    private final CategoryByUuidFinder categoryByUuidFinder;

    public CategoryGetByUuidController(final CategoryByUuidFinder categoryByUuidFinder) {
        this.categoryByUuidFinder = categoryByUuidFinder;
    }

    @GetMapping(UUID_PATH_VAR)
    public ResponseEntity<CategoryResponse> get(@PathVariable final String uuid) {
        return ResponseEntity.ok(categoryByUuidFinder.find(uuid));
    }

}
