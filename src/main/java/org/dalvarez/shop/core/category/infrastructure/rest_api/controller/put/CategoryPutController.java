package org.dalvarez.shop.core.category.infrastructure.rest_api.controller.put;

import org.dalvarez.shop.core.category.application.CategoryResponse;
import org.dalvarez.shop.core.category.application.update.CategoryUpdater;
import org.dalvarez.shop.core.category.infrastructure.rest_api.controller.CategoryApiController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public final class CategoryPutController extends CategoryApiController {

    private final CategoryUpdater categoryUpdater;

    public CategoryPutController(final CategoryUpdater categoryUpdater) {
        this.categoryUpdater = categoryUpdater;
    }

    @PutMapping(ID_PATH_VAR)
    public ResponseEntity<CategoryResponse> put(@PathVariable final Long id,
                                                @RequestBody final CategoryPutRequest categoryPutRequest) {
        return ResponseEntity.ok(categoryUpdater.update(categoryPutRequest.toCategory(id)));
    }

}
