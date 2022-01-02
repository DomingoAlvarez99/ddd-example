package org.dalvarez.ddd_example.category.infrastructure.rest_api.controller.get.by_criteria;

import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.category.application.CategoryQueryResultResponse;
import org.dalvarez.ddd_example.category.application.find.by_criteria.CategoryByCriteriaFinder;
import org.dalvarez.ddd_example.category.infrastructure.rest_api.controller.CategoryApiController;
import org.dalvarez.ddd_example.shared.domain.criteria.Criteria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class CategoryGetByCriteriaController extends CategoryApiController {

    private final CategoryByCriteriaFinder categoryByCriteriaFinder;

    public CategoryGetByCriteriaController(final CategoryByCriteriaFinder categoryByCriteriaFinder) {
        this.categoryByCriteriaFinder = categoryByCriteriaFinder;
    }

    @GetMapping(SEARCH_PATH)
    public ResponseEntity<CategoryQueryResultResponse> get(@RequestParam(required = false) final String orderField,
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

        return ResponseEntity.ok(categoryByCriteriaFinder.find(criteria));
    }

}
