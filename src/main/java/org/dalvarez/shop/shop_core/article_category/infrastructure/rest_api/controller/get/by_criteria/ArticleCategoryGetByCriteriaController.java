package org.dalvarez.shop.shop_core.article_category.infrastructure.rest_api.controller.get.by_criteria;

import org.dalvarez.shop.shop_common.persistence.application.criteria.QueryResultResponse;
import org.dalvarez.shop.shop_common.persistence.domain.criteria.Criteria;
import org.dalvarez.shop.shop_core.article_category.application.ArticleCategoryResponse;
import org.dalvarez.shop.shop_core.article_category.application.find.by_criteria.ArticleCategoryByCriteriaFinder;
import org.dalvarez.shop.shop_core.article_category.infrastructure.rest_api.controller.ArticleCategoryApiController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class ArticleCategoryGetByCriteriaController extends ArticleCategoryApiController {

    private final ArticleCategoryByCriteriaFinder articleCategoryByCriteriaFinder;

    public ArticleCategoryGetByCriteriaController(final ArticleCategoryByCriteriaFinder articleCategoryByCriteriaFinder) {
        this.articleCategoryByCriteriaFinder = articleCategoryByCriteriaFinder;
    }

    @GetMapping(SEARCH_PATH)
    public ResponseEntity<QueryResultResponse<ArticleCategoryResponse>> get(@RequestParam(required = false) final String orderField,
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

        return ResponseEntity.ok(articleCategoryByCriteriaFinder.find(criteria));
    }

}
