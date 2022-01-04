package org.dalvarez.ddd_example.article.infrastructure.rest_api.controller.get.by_id;

import org.dalvarez.ddd_example.article.application.ArticleResponse;
import org.dalvarez.ddd_example.article.application.find.by_id.ArticleByIdFinder;
import org.dalvarez.ddd_example.article.infrastructure.rest_api.controller.ArticleApiController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.dalvarez.ddd_example.shared.infrastructure.rest_api.controller.ApiConstants.ID_PATH_VAR;

@RestController
public final class ArticleGetByIdController extends ArticleApiController {

    private final ArticleByIdFinder articleByIdFinder;

    public ArticleGetByIdController(final ArticleByIdFinder articleByIdFinder) {
        this.articleByIdFinder = articleByIdFinder;
    }

    @GetMapping(ID_PATH_VAR)
    public ResponseEntity<ArticleResponse> get(@PathVariable final String id) {
        return ResponseEntity.ok(articleByIdFinder.find(id));
    }

}
