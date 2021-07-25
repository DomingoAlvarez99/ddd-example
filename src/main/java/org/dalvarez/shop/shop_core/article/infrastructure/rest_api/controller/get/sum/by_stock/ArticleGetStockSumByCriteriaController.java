package org.dalvarez.shop.shop_core.article.infrastructure.rest_api.controller.get.sum.by_stock;

import org.dalvarez.shop.shop_common.persistence.application.shared.sum.SumResponse;
import org.dalvarez.shop.shop_common.persistence.domain.criteria.Criteria;
import org.dalvarez.shop.shop_core.article.application.sum.by_stock.ArticleByStockAdder;
import org.dalvarez.shop.shop_core.article.infrastructure.rest_api.controller.ArticleApiController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class ArticleGetStockSumByCriteriaController extends ArticleApiController {

    private final ArticleByStockAdder articleByStockAdder;

    public ArticleGetStockSumByCriteriaController(final ArticleByStockAdder articleByStockAdder) {
        this.articleByStockAdder = articleByStockAdder;
    }

    @GetMapping(STOCK_SUM_BY_CRITERIA_PATH)
    public ResponseEntity<SumResponse<Integer>> get(@RequestParam(required = false) final String orderField,
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

        return ResponseEntity.ok(articleByStockAdder.sum(criteria));
    }

}
