package org.dalvarez.shop.shop_core.article_category.infrastructure.rest_api.controller.delete;

import org.dalvarez.shop.shop_core.article_category.application.erase.ArticleCategoryEraser;
import org.dalvarez.shop.shop_core.article_category.infrastructure.rest_api.controller.ArticleCategoryApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class ArticleCategoryDeleteController extends ArticleCategoryApiController {

    private final ArticleCategoryEraser articleCategoryEraser;

    public ArticleCategoryDeleteController(final ArticleCategoryEraser articleCategoryEraser) {
        this.articleCategoryEraser = articleCategoryEraser;
    }

    @DeleteMapping(ID_PATH_VAR)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        articleCategoryEraser.erase(id);

        return ResponseEntity.noContent()
                             .build();
    }

}
