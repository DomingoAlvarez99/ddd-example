package org.dalvarez.shop.core.article.infrastructure.rest_api.post;

import org.dalvarez.shop.core.article.application.ArticleResponse;
import org.dalvarez.shop.core.article.domain.Article;
import org.dalvarez.shop.core.article.domain.ArticleRepository;
import org.dalvarez.shop.core.article.infrastructure.ArticleInfrastructureRestApiModuleTestCase;
import org.dalvarez.shop.core.article.infrastructure.rest_api.controller.post.ArticlePostRequest;
import org.dalvarez.shop.core.shared.domain.log.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class ArticlePostControllerShouldItTestCase extends ArticleInfrastructureRestApiModuleTestCase {

    private static final Integer STOCK = 100;

    private static final Double PRICE = 2.25;

    private static final String NAME = "Dark souls 3";

    private static final String DESCRIPTION = "Desc 1";

    protected Logger log;

    protected ArticlePostControllerShouldItTestCase(@Autowired ArticleRepository repository,
                                                    @Autowired Logger log) {
        super(repository, log);
        this.log = log;
    }

    @Test
    void shouldPostAnArticle() throws Exception {
        final Article request = new ArticlePostRequest(
                STOCK,
                PRICE,
                NAME,
                DESCRIPTION
        ).toArticle();

        final ArticleResponse expected = shouldPost(request, ArticleResponse.class);

        final ArticleResponse actual = shouldGetById(expected.getId(), ArticleResponse.class);

        assertEquals(expected, actual);
    }

}
