package org.dalvarez.shop.shop_core.article.infrastructure.rest_api.controller.get.by_criteria;

import org.dalvarez.shop.shop_common.persistence.application.criteria.QueryResultResponse;
import org.dalvarez.shop.shop_common.persistence.domain.criteria.Criteria;
import org.dalvarez.shop.shop_core.article.application.ArticleResponse;
import org.dalvarez.shop.shop_core.article.application.find.by_criteria.ArticleByCriteriaFinder;
import org.dalvarez.shop.shop_core.article.infrastructure.rest_api.controller.ArticleApiController;
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
    public ResponseEntity<QueryResultResponse<ArticleResponse>> get(@RequestParam(required = false) final String orderField,
                                                                    @RequestParam(required = false) final String orderType,
                                                                    @RequestParam(required = false) final String filtersBooleanOperator,
                                                                    @RequestParam(required = false) final String filtersValues,
                                                                    @RequestParam(required = false) final Long pageIndex,
                                                                    @RequestParam(required = false) final Long pageSize) {
        final Criteria criteria = Criteria.fromQuery(
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
