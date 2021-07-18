package org.dalvarez.shop.core.article.infrastructure.rest_api.controller.get.sum.by_price;

import org.dalvarez.shop.core.article.application.sum.by_price.ArticleByPriceAdder;
import org.dalvarez.shop.core.article.infrastructure.rest_api.controller.ArticleApiController;
import org.dalvarez.shop.shared.persistence.application.shared.sum.SumResponse;
import org.dalvarez.shop.shared.persistence.domain.criteria.Criteria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public final class ArticleGetPriceSumByCriteriaController extends ArticleApiController {

    private final ArticleByPriceAdder articleByPriceAdder;

    public ArticleGetPriceSumByCriteriaController(final ArticleByPriceAdder articleByPriceAdder) {
        this.articleByPriceAdder = articleByPriceAdder;
    }

    @GetMapping(PRICE_SUM_BY_CRITERIA_PATH)
    public ResponseEntity<SumResponse<Double>> get(@RequestParam(required = false) final String orderField,
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

        return ResponseEntity.ok(articleByPriceAdder.sum(criteria));
    }

}
