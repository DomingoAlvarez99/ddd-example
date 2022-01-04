package org.dalvarez.ddd_example.article.infrastructure.rest_api.controller.get.count;

import org.dalvarez.ddd_example.article.application.count.by_criteria.ArticleByCriteriaCounter;
import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.article.infrastructure.rest_api.controller.ArticleApiController;
import org.dalvarez.ddd_example.shared.application.response.CountResultResponse;
import org.dalvarez.ddd_example.shared.domain.criteria.Criteria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.dalvarez.ddd_example.shared.infrastructure.rest_api.controller.ApiConstants.COUNT_PATH;
import static org.dalvarez.ddd_example.shared.infrastructure.rest_api.controller.ApiConstants.SEARCH_PATH;

@RestController
public final class ArticleGetCountByCriteriaController extends ArticleApiController {

    public static final String COUNT_BY_CRITERIA_PATH = COUNT_PATH + SEARCH_PATH;

    private final ArticleByCriteriaCounter articleByCriteriaCounter;

    public ArticleGetCountByCriteriaController(final ArticleByCriteriaCounter articleByCriteriaCounter) {
        this.articleByCriteriaCounter = articleByCriteriaCounter;
    }

    @GetMapping(COUNT_BY_CRITERIA_PATH)
    public ResponseEntity<CountResultResponse> count(@RequestParam(required = false) final String orderField,
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

        return ResponseEntity.ok(articleByCriteriaCounter.count(criteria));
    }

}
