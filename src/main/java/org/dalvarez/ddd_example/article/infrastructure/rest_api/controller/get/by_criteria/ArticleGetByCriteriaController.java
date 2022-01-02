package org.dalvarez.ddd_example.article.infrastructure.rest_api.controller.get.by_criteria;

import org.dalvarez.ddd_example.article.application.ArticleQueryResultResponse;
import org.dalvarez.ddd_example.article.application.find.by_criteria.ArticleByCriteriaFinder;
import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.article.infrastructure.rest_api.controller.ArticleApiController;
import org.dalvarez.ddd_example.shared.domain.criteria.Criteria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class ArticleGetByCriteriaController extends ArticleApiController {

    private final ArticleByCriteriaFinder articleByCriteriaFinder;

    public ArticleGetByCriteriaController(final ArticleByCriteriaFinder articleByCriteriaFinder) {
        this.articleByCriteriaFinder = articleByCriteriaFinder;
    }

    @GetMapping(SEARCH_PATH)
    public ResponseEntity<ArticleQueryResultResponse> get(@RequestParam(required = false) final String orderField,
                                                          @RequestParam(required = false) final String orderType,
                                                          @RequestParam(required = false) final String filtersBooleanOperator,
                                                          @RequestParam(required = false) final String filtersValues,
                                                          @RequestParam(required = false) final Long pageIndex,
                                                          @RequestParam(required = false) final Long pageSize) {
        final Criteria criteria = Criteria.fromQuery(
                Article.class,
                orderField,
                orderType,
                filtersBooleanOperator,
                filtersValues,
                pageIndex,
                pageSize
        );

        return ResponseEntity.ok(articleByCriteriaFinder.find(criteria));
    }

}
