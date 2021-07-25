package org.dalvarez.shop.shop_core.article_category.infrastructure.rest_api.controller.get.by_uuid;

import org.dalvarez.shop.shop_core.article_category.application.ArticleCategoryResponse;
import org.dalvarez.shop.shop_core.article_category.application.find.by_uuid.ArticleCategoryByUuidFinder;
import org.dalvarez.shop.shop_core.article_category.infrastructure.rest_api.controller.ArticleCategoryApiController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class ArticleCategoryGetByUuidController extends ArticleCategoryApiController {

    private final ArticleCategoryByUuidFinder articleCategoryByUuidFinder;

    public ArticleCategoryGetByUuidController(final ArticleCategoryByUuidFinder articleCategoryByUuidFinder) {
        this.articleCategoryByUuidFinder = articleCategoryByUuidFinder;
    }

    @GetMapping
    public ResponseEntity<ArticleCategoryResponse> get(@RequestParam(required = false) final String uuid) {
        return ResponseEntity.ok(articleCategoryByUuidFinder.find(uuid));
    }

}
