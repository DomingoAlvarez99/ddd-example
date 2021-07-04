package org.dalvarez.shop.core.article_category.infrastructure.rest_api.controller.get.by_uuid;

import org.dalvarez.shop.core.article_category.application.ArticleCategoryResponse;
import org.dalvarez.shop.core.article_category.application.find.by_uuid.ArticleCategoryByUuidFinder;
import org.dalvarez.shop.core.article_category.infrastructure.rest_api.controller.ArticleCategoryApiController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
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
