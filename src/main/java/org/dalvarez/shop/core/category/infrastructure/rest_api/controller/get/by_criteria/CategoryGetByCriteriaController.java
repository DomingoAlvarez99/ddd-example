package org.dalvarez.shop.core.category.infrastructure.rest_api.controller.get.by_criteria;

import org.dalvarez.shop.core.category.application.CategoryResponse;
import org.dalvarez.shop.core.category.application.find.by_criteria.CategoryByCriteriaFinder;
import org.dalvarez.shop.core.category.infrastructure.rest_api.controller.CategoryApiController;
import org.dalvarez.shop.shared.persistence.application.QueryResultResponse;
import org.dalvarez.shop.shared.persistence.domain.criteria.Criteria;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public final class CategoryGetByCriteriaController extends CategoryApiController {

    private final CategoryByCriteriaFinder categoryByCriteriaFinder;

    public CategoryGetByCriteriaController(final CategoryByCriteriaFinder categoryByCriteriaFinder) {
        this.categoryByCriteriaFinder = categoryByCriteriaFinder;
    }

    @GetMapping(SEARCH_PATH)
    public ResponseEntity<QueryResultResponse<CategoryResponse>> get(@RequestParam(required = false) final String orderField,
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

        return ResponseEntity.ok(categoryByCriteriaFinder.find(criteria));
    }

}
