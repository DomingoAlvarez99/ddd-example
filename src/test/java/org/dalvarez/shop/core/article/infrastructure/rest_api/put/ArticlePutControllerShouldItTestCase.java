package org.dalvarez.shop.core.article.infrastructure.rest_api.put;

import org.dalvarez.shop.core.article.application.ArticleResponse;
import org.dalvarez.shop.core.article.domain.Article;
import org.dalvarez.shop.core.article.domain.ArticleMother;
import org.dalvarez.shop.core.article.domain.ArticleRepository;
import org.dalvarez.shop.core.article.infrastructure.ArticleInfrastructureRestApiModuleTestCase;
import org.dalvarez.shop.core.article.infrastructure.rest_api.controller.put.ArticlePutRequest;
import org.dalvarez.shop.core.shared.domain.log.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class ArticlePutControllerShouldItTestCase extends ArticleInfrastructureRestApiModuleTestCase {

    private static final Integer STOCK = 100;

    private static final Double PRICE = 2.25;

    private static final String NAME = "Dark souls 3";

    private static final String DESCRIPTION = "Desc 1";

    protected Logger log;

    protected ArticlePutControllerShouldItTestCase(@Autowired ArticleRepository repository,
                                                   @Autowired Logger log) {
        super(repository, log);
        this.log = log;
    }

    @Test
    void shouldPutAnArticle() throws Exception {
        final Article article = data.get(1);

        final ArticlePutRequest request = new ArticlePutRequest(
                STOCK,
                PRICE,
                NAME,
                DESCRIPTION
        );

        final ArticleResponse expected = shouldPut(request, ArticleResponse.class, article.getId());

        final ArticleResponse actual = shouldGetById(expected.getId(), ArticleResponse.class);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getStock(), actual.getStock());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void shouldNotPutAnArticleCauseNotExist() throws Exception {
        final ArticlePutRequest request = new ArticlePutRequest(
                STOCK,
                PRICE,
                NAME,
                DESCRIPTION
        );

        final Long id = 100000L;

        shouldNotPutCauseNotExist(request, id);
    }


    @Test
    void shouldNotUpdateAnUuidCauseIsNotUpdatable() throws Exception {
        final ArticlePutRequest request = new ArticlePutRequest(
                STOCK,
                PRICE,
                NAME,
                DESCRIPTION
        );

        log.info("Request {}", request.toString());

        shouldNotPutCauseNotExist(request, 100000000L);
    }

}
