package org.dalvarez.shop.shop_core.article_category.infrastructure.rest_api.controller.put;

import org.dalvarez.shop.shop_core.article_category.application.ArticleCategoryResponse;
import org.dalvarez.shop.shop_core.article_category.application.update.ArticleCategoryUpdater;
import org.dalvarez.shop.shop_core.article_category.infrastructure.rest_api.controller.ArticleCategoryApiController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class ArticleCategoryPutController extends ArticleCategoryApiController {

    private final ArticleCategoryUpdater articleCategoryUpdater;

    public ArticleCategoryPutController(final ArticleCategoryUpdater articleCategoryUpdater) {
        this.articleCategoryUpdater = articleCategoryUpdater;
    }

    @PutMapping(UUID_PATH_VAR)
    public ResponseEntity<ArticleCategoryResponse> put(@PathVariable final String uuid,
                                                       @RequestBody final ArticleCategoryPutRequest articleCategoryPutRequest) {
        return ResponseEntity.ok(articleCategoryUpdater.update(articleCategoryPutRequest.toArticleCategory(uuid)));
    }

}