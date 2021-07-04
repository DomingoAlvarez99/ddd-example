package org.dalvarez.shop.core.article_category.infrastructure.rest_api.controller;

import org.dalvarez.shop.core.shared.infrastructure.rest_api.ApiController;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ArticleCategoryApiController.ARTICLES_CATEGORIES_PATH)
public class ArticleCategoryApiController extends ApiController {

    public static final String ARTICLES_CATEGORIES_PATH = "/articlesCategories";

}
