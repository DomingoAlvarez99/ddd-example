package org.dalvarez.shop.shop_core.article.infrastructure.rest_api.controller.delete;

import org.dalvarez.shop.shop_core.article.application.erase.ArticleEraser;
import org.dalvarez.shop.shop_core.article.infrastructure.rest_api.controller.ArticleApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class ArticleDeleteController extends ArticleApiController {

    private final ArticleEraser articleEraser;

    public ArticleDeleteController(final ArticleEraser articleEraser) {
        this.articleEraser = articleEraser;
    }

    @DeleteMapping(UUID_PATH_VAR)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable final String uuid) {
        articleEraser.erase(uuid);

        return ResponseEntity.noContent()
                             .build();
    }

}
