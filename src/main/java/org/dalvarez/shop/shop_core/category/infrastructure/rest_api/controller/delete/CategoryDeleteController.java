package org.dalvarez.shop.shop_core.category.infrastructure.rest_api.controller.delete;

import org.dalvarez.shop.shop_core.category.application.erase.CategoryEraser;
import org.dalvarez.shop.shop_core.category.infrastructure.rest_api.controller.CategoryApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class CategoryDeleteController extends CategoryApiController {

    private final CategoryEraser categoryEraser;

    public CategoryDeleteController(final CategoryEraser categoryEraser) {
        this.categoryEraser = categoryEraser;
    }

    @DeleteMapping(UUID_PATH_VAR)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable final String uuid) {
        categoryEraser.erase(uuid);

        return ResponseEntity.noContent()
                             .build();
    }

}
