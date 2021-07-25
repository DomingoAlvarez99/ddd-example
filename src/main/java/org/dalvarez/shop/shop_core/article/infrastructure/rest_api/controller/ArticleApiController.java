package org.dalvarez.shop.shop_core.article.infrastructure.rest_api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.dalvarez.shop.shop_common.rest_api.infrastructure.ApiController;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ArticleApiController.ARTICLES_PATH)
@Tag(name = ArticleApiController.ARTICLES_PATH)
public class ArticleApiController extends ApiController {

    public static final String ARTICLES_PATH = "/articles";

    public static final String PRICE_PATH = "/price";

    public static final String STOCK_PATH = "/stock";

    public static final String PRICE_SUM_BY_CRITERIA_PATH = SUM_PATH + PRICE_PATH + SEARCH_PATH;

    public static final String STOCK_SUM_BY_CRITERIA_PATH = SUM_PATH + STOCK_PATH + SEARCH_PATH;

    public static final String COUNT_BY_CRITERIA_PATH = COUNT_PATH + SEARCH_PATH;

}
