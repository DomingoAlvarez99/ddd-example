package org.dalvarez.shop.core.category.infrastructure.rest_api.controller;

import org.dalvarez.shop.core.shared.infrastructure.rest_api.ApiController;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(CategoryApiController.CATEGORIES_PATH)
public class CategoryApiController extends ApiController {

    public static final String CATEGORIES_PATH = "/categories";

}
