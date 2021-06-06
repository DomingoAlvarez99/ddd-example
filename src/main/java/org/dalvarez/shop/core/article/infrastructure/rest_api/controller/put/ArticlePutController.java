package org.dalvarez.shop.core.article.infrastructure.rest_api.controller.put;

import org.dalvarez.shop.core.article.application.ArticleResponse;
import org.dalvarez.shop.core.article.application.update.ArticleUpdater;
import org.dalvarez.shop.core.article.infrastructure.rest_api.controller.ArticleApiController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public final class ArticlePutController extends ArticleApiController {

    private final ArticleUpdater articleUpdater;

    public ArticlePutController(final ArticleUpdater articleUpdater) {
        this.articleUpdater = articleUpdater;
    }

    @PutMapping(ID_PATH_VAR)
    public ResponseEntity<ArticleResponse> put(@PathVariable final Long id,
                                               @RequestBody final ArticlePutRequest articlePutRequest) {
        return ResponseEntity.ok(articleUpdater.update(articlePutRequest.toArticle(id)));
    }

}
