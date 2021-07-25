package org.dalvarez.shop.shop_core.article_category.infrastructure.rest_api.controller.post;

import org.dalvarez.shop.shop_core.article_category.application.ArticleCategoryResponse;
import org.dalvarez.shop.shop_core.article_category.application.create.ArticleCategoryCreator;
import org.dalvarez.shop.shop_core.article_category.infrastructure.rest_api.controller.ArticleCategoryApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class ArticleCategoryPostController extends ArticleCategoryApiController {

    private final ArticleCategoryCreator articleCategoryCreator;

    public ArticleCategoryPostController(final ArticleCategoryCreator articleCategoryCreator) {
        this.articleCategoryCreator = articleCategoryCreator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ArticleCategoryResponse> post(@RequestBody final ArticleCategoryPostRequest articleCategoryPostRequest) {
        return new ResponseEntity<>(
                articleCategoryCreator.create(articleCategoryPostRequest.toArticleCategory()),
                HttpStatus.CREATED
        );
    }

}
