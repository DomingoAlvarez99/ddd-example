package org.dalvarez.shop.shop_core.article_category.infrastructure.rest_api.controller.get.by_uuid;

import org.dalvarez.shop.shop_core.article_category.application.ArticleCategoryResponse;
import org.dalvarez.shop.shop_core.article_category.application.find.by_uuid.ArticleCategoryByUuidFinder;
import org.dalvarez.shop.shop_core.article_category.infrastructure.rest_api.controller.ArticleCategoryApiController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class ArticleCategoryGetByUuidController extends ArticleCategoryApiController {

    private final ArticleCategoryByUuidFinder articleCategoryByUuidFinder;

    public ArticleCategoryGetByUuidController(final ArticleCategoryByUuidFinder articleCategoryByUuidFinder) {
        this.articleCategoryByUuidFinder = articleCategoryByUuidFinder;
    }

    @GetMapping(UUID_PATH_VAR)
    public ResponseEntity<ArticleCategoryResponse> get(@PathVariable final String uuid) {
        return ResponseEntity.ok(articleCategoryByUuidFinder.find(uuid));
    }

}
