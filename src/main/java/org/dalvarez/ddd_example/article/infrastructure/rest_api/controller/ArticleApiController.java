package org.dalvarez.ddd_example.article.infrastructure.rest_api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ArticleApiController.ARTICLES_PATH)
@Tag(name = ArticleApiController.ARTICLES_PATH)
public class ArticleApiController {

    public static final String ARTICLES_PATH = "/articles";

}
