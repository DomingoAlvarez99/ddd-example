package org.dalvarez.shop.core.article.infrastructure.rest_api.controller.delete;

import org.dalvarez.shop.core.article.application.erase.ArticleEraser;
import org.dalvarez.shop.core.article.infrastructure.rest_api.controller.ArticleApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

public final class ArticleDeleteController extends ArticleApiController {

    private final ArticleEraser articleEraser;

    public ArticleDeleteController(final ArticleEraser articleEraser) {
        this.articleEraser = articleEraser;
    }

    @DeleteMapping(ID_PATH_VAR)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        articleEraser.erase(id);

        return ResponseEntity.noContent()
                             .build();
    }

}
