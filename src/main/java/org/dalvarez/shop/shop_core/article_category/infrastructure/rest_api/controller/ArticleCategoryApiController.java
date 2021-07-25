package org.dalvarez.shop.shop_core.article_category.infrastructure.rest_api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.dalvarez.shop.shop_common.rest_api.infrastructure.ApiController;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ArticleCategoryApiController.ARTICLES_CATEGORIES_PATH)
@Tag(name = ArticleCategoryApiController.ARTICLES_CATEGORIES_PATH)
public class ArticleCategoryApiController extends ApiController {

    public static final String ARTICLES_CATEGORIES_PATH = "/articlesCategories";

}
