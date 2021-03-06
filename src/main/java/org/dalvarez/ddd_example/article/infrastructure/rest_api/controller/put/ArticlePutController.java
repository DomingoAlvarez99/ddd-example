package org.dalvarez.ddd_example.article.infrastructure.rest_api.controller.put;

import org.dalvarez.ddd_example.article.application.ArticleRequest;
import org.dalvarez.ddd_example.article.application.update.ArticleUpdater;
import org.dalvarez.ddd_example.article.infrastructure.rest_api.controller.ArticleApiController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.dalvarez.ddd_example.shared.infrastructure.rest_api.controller.ApiConstants.ID_PATH_VAR;

@RestController
public final class ArticlePutController extends ArticleApiController {

    private final ArticleUpdater articleUpdater;

    public ArticlePutController(final ArticleUpdater articleUpdater) {
        this.articleUpdater = articleUpdater;
    }

    @PutMapping(ID_PATH_VAR)
    public ResponseEntity<Void> put(@PathVariable final String id,
                                    @RequestBody final ArticleRequest articleRequest) {
        articleUpdater.update(id, articleRequest);

        return ResponseEntity.ok().build();
    }

}
