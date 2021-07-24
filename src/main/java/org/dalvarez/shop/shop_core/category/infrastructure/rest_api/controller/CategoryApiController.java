package org.dalvarez.shop.shop_core.category.infrastructure.rest_api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.dalvarez.shop.shop_core.shared.infrastructure.rest_api.ApiController;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(CategoryApiController.CATEGORIES_PATH)
@Tag(name = CategoryApiController.CATEGORIES_PATH)
public class CategoryApiController extends ApiController {

    public static final String CATEGORIES_PATH = "/categories";

}
