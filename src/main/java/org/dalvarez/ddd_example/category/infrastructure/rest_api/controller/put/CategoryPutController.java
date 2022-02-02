package org.dalvarez.ddd_example.category.infrastructure.rest_api.controller.put;

import org.dalvarez.ddd_example.category.application.CategoryRequest;
import org.dalvarez.ddd_example.category.application.update.CategoryUpdater;
import org.dalvarez.ddd_example.category.infrastructure.rest_api.controller.CategoryApiController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.dalvarez.ddd_example.shared.infrastructure.rest_api.controller.ApiConstants.ID_PATH_VAR;

@RestController
public final class CategoryPutController extends CategoryApiController {

    private final CategoryUpdater categoryUpdater;

    public CategoryPutController(final CategoryUpdater categoryUpdater) {
        this.categoryUpdater = categoryUpdater;
    }

    @PutMapping(ID_PATH_VAR)
    public ResponseEntity<Void> put(@PathVariable final String id,
                                    @RequestBody final CategoryRequest categoryRequest) {
        categoryUpdater.update(id, categoryRequest);

        return ResponseEntity.ok().build();
    }

}
