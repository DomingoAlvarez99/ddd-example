package org.dalvarez.ddd_example.article.infrastructure.rest_api.controller.post;

import org.dalvarez.ddd_example.article.application.ArticleRequest;
import org.dalvarez.ddd_example.article.application.create.ArticleCreator;
import org.dalvarez.ddd_example.article.infrastructure.rest_api.controller.ArticleApiController;
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
    public ResponseEntity<Void> post(@RequestBody final ArticleRequest articleRequest) {
        articleCreator.create(articleRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
