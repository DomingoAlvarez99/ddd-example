package org.dalvarez.shop.shop_core.article_category.infrastructure.rest_api.controller.get.by_id;

import org.dalvarez.shop.shop_core.article_category.application.ArticleCategoryResponse;
import org.dalvarez.shop.shop_core.article_category.application.find.by_id.ArticleCategoryByIdFinder;
import org.dalvarez.shop.shop_core.article_category.infrastructure.rest_api.controller.ArticleCategoryApiController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public final class ArticleCategoryGetByIdController extends ArticleCategoryApiController {

    private final ArticleCategoryByIdFinder articleCategoryByIdFinder;

    public ArticleCategoryGetByIdController(final ArticleCategoryByIdFinder articleCategoryByIdFinder) {
        this.articleCategoryByIdFinder = articleCategoryByIdFinder;
    }

    @GetMapping(ID_PATH_VAR)
    public ResponseEntity<ArticleCategoryResponse> get(@PathVariable final Long id) {
        return ResponseEntity.ok(articleCategoryByIdFinder.find(id));
    }

}
