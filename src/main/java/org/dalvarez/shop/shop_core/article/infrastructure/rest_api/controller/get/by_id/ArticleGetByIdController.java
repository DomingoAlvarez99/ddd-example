package org.dalvarez.shop.shop_core.article.infrastructure.rest_api.controller.get.by_id;

import org.dalvarez.shop.shop_core.article.application.ArticleResponse;
import org.dalvarez.shop.shop_core.article.application.find.by_id.ArticleByIdFinder;
import org.dalvarez.shop.shop_core.article.infrastructure.rest_api.controller.ArticleApiController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public final class ArticleGetByIdController extends ArticleApiController {

    private final ArticleByIdFinder articleByIdFinder;

    public ArticleGetByIdController(final ArticleByIdFinder articleByIdFinder) {
        this.articleByIdFinder = articleByIdFinder;
    }

    @GetMapping(ID_PATH_VAR)
    public ResponseEntity<ArticleResponse> get(@PathVariable final Long id) {
        return ResponseEntity.ok(articleByIdFinder.find(id));
    }

}
