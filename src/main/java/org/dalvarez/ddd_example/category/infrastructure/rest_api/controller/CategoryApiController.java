package org.dalvarez.ddd_example.category.infrastructure.rest_api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.dalvarez.ddd_example.shared.infrastructure.rest_api.controller.ApiController;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(CategoryApiController.CATEGORIES_PATH)
@Tag(name = CategoryApiController.CATEGORIES_PATH)
public class CategoryApiController extends ApiController {

    public static final String CATEGORIES_PATH = "/categories";

}
