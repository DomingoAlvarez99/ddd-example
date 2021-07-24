package org.dalvarez.shop.shop_core.category.infrastructure.rest_api.controller.get.by_uuid;

import org.dalvarez.shop.shop_core.category.application.CategoryResponse;
import org.dalvarez.shop.shop_core.category.application.find.by_uuid.CategoryByUuidFinder;
import org.dalvarez.shop.shop_core.category.infrastructure.rest_api.controller.CategoryApiController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public final class CategoryGetByUuidController extends CategoryApiController {

    private final CategoryByUuidFinder categoryByUuidFinder;

    public CategoryGetByUuidController(final CategoryByUuidFinder categoryByUuidFinder) {
        this.categoryByUuidFinder = categoryByUuidFinder;
    }

    @GetMapping
    public ResponseEntity<CategoryResponse> get(@RequestParam(required = false) final String uuid) {
        return ResponseEntity.ok(categoryByUuidFinder.find(uuid));
    }

}
