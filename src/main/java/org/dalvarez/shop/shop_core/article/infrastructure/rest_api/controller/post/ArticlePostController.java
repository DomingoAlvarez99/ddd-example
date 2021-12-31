package org.dalvarez.shop.shop_core.article.infrastructure.rest_api.controller.post;

import org.dalvarez.shop.shop_core.article.application.ArticleRequest;
import org.dalvarez.shop.shop_core.article.application.ArticleResponse;
import org.dalvarez.shop.shop_core.article.application.create.ArticleCreator;
import org.dalvarez.shop.shop_core.article.infrastructure.rest_api.controller.ArticleApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class ArticlePostController extends ArticleApiController {

    private final ArticleCreator articleCreator;

    public ArticlePostController(final ArticleCreator articleCreator) {
        this.articleCreator = articleCreator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ArticleResponse> post(@RequestBody final ArticleRequest articleRequest) {
        return new ResponseEntity<>(
                articleCreator.create(articleRequest),
                HttpStatus.CREATED
        );
    }

}
