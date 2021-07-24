package org.dalvarez.shop.shop_core.category.infrastructure.rest_api.controller.delete;

import org.dalvarez.shop.shop_core.category.application.erase.CategoryEraser;
import org.dalvarez.shop.shop_core.category.infrastructure.rest_api.controller.CategoryApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public final class CategoryDeleteController extends CategoryApiController {

    private final CategoryEraser categoryEraser;

    public CategoryDeleteController(final CategoryEraser categoryEraser) {
        this.categoryEraser = categoryEraser;
    }

    @DeleteMapping(ID_PATH_VAR)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        categoryEraser.erase(id);

        return ResponseEntity.noContent()
                             .build();
    }

}
