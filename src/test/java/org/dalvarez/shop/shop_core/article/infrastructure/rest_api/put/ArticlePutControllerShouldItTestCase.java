package org.dalvarez.shop.shop_core.article.infrastructure.rest_api.put;

import org.dalvarez.shop.shop_core.article.application.ArticleRequest;
import org.dalvarez.shop.shop_core.article.application.ArticleResponse;
import org.dalvarez.shop.shop_core.article.domain.model.Article;
import org.dalvarez.shop.shop_core.article.domain.port.ArticleRepository;
import org.dalvarez.shop.shop_core.article.infrastructure.ArticleInfrastructureRestApiModuleTestCase;
import org.dalvarez.shop.shop_common.shared.domain.log.Logger;
import org.dalvarez.shop.shop_core.shared.domain.IdMother;
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

        final ArticleRequest request = ArticleRequest.of(
                STOCK,
                PRICE,
                NAME,
                DESCRIPTION,
                null
        );

        final ArticleResponse expected = shouldPut(request, ArticleResponse.class, article.id().value());

        log.info("{}", expected.toString());
        final ArticleResponse actual = shouldgetById(expected.id(), ArticleResponse.class);

        assertEquals(expected.id(), actual.id());
    }

    @Test
    void shouldNotPutAnArticleCauseNotExist() throws Exception {
        final ArticleRequest request =  ArticleRequest.of(
                STOCK,
                PRICE,
                NAME,
                DESCRIPTION,
                null
        );

        final String id = IdMother.randomGeneration(888);

        shouldNotPutCauseNotExist(request, id);
    }

}
