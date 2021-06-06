package org.dalvarez.shop.core.article.infrastructure.rest_api.controller.get.by_uuid;

import org.dalvarez.shop.core.article.application.ArticleResponse;
import org.dalvarez.shop.core.article.application.find.by_uuid.ArticleByUuidFinder;
import org.dalvarez.shop.core.article.infrastructure.rest_api.controller.ArticleApiController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public final class ArticleGetByUuidController extends ArticleApiController {

    private final ArticleByUuidFinder articleByUuidFinder;

    public ArticleGetByUuidController(final ArticleByUuidFinder articleByUuidFinder) {
        this.articleByUuidFinder = articleByUuidFinder;
    }

    @GetMapping
    public ResponseEntity<ArticleResponse> get(@RequestParam(required = false) final String uuid) {
        return ResponseEntity.ok(articleByUuidFinder.find(uuid));
    }

}
