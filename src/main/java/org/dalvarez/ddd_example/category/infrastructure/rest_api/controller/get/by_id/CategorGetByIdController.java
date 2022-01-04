package org.dalvarez.ddd_example.category.infrastructure.rest_api.controller.get.by_id;

import org.dalvarez.ddd_example.category.application.CategoryResponse;
import org.dalvarez.ddd_example.category.application.find.by_id.CategoryByIdFinder;
import org.dalvarez.ddd_example.category.infrastructure.rest_api.controller.CategoryApiController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.dalvarez.ddd_example.shared.infrastructure.rest_api.controller.ApiConstants.ID_PATH_VAR;

@RestController
public final class CategorGetByIdController extends CategoryApiController {

    private final CategoryByIdFinder categoryByIdFinder;

    public CategorGetByIdController(final CategoryByIdFinder categoryByIdFinder) {
        this.categoryByIdFinder = categoryByIdFinder;
    }

    @GetMapping(ID_PATH_VAR)
    public ResponseEntity<CategoryResponse> get(@PathVariable final String id) {
        return ResponseEntity.ok(categoryByIdFinder.find(id));
    }

}
