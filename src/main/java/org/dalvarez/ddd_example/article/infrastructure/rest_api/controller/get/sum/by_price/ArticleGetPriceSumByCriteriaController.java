package org.dalvarez.ddd_example.article.infrastructure.rest_api.controller.get.sum.by_price;

import org.dalvarez.ddd_example.article.application.sum.by_price.ArticleByPriceAdder;
import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.article.infrastructure.rest_api.controller.ArticleApiController;
import org.dalvarez.ddd_example.shared.application.response.SumResponse;
import org.dalvarez.ddd_example.shared.domain.criteria.Criteria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.dalvarez.ddd_example.shared.infrastructure.rest_api.controller.ApiConstants.SEARCH_PATH;
import static org.dalvarez.ddd_example.shared.infrastructure.rest_api.controller.ApiConstants.SUM_PATH;

@RestController
public final class ArticleGetPriceSumByCriteriaController extends ArticleApiController {

    public static final String PRICE_SUM_BY_CRITERIA_PATH = SUM_PATH + "/price" + SEARCH_PATH;

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
