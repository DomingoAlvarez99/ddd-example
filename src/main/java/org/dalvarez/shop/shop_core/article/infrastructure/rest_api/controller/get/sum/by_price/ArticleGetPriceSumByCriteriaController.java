package org.dalvarez.shop.shop_core.article.infrastructure.rest_api.controller.get.sum.by_price;

import org.dalvarez.shop.shop_common.persistence.application.shared.sum.SumResponse;
import org.dalvarez.shop.shop_common.persistence.domain.criteria.Criteria;
import org.dalvarez.shop.shop_core.article.application.sum.by_price.ArticleByPriceAdder;
import org.dalvarez.shop.shop_core.article.domain.model.Article;
import org.dalvarez.shop.shop_core.article.infrastructure.rest_api.controller.ArticleApiController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
                Article.class,
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
