package org.dalvarez.shop.shop_core.article.infrastructure.rest_api.get.by_id;

import org.dalvarez.shop.shop_core.article.application.ArticleResponse;
import org.dalvarez.shop.shop_core.article.domain.model.Article;
import org.dalvarez.shop.shop_core.article.domain.ArticleMother;
import org.dalvarez.shop.shop_core.article.domain.port.ArticleRepository;
import org.dalvarez.shop.shop_core.article.infrastructure.ArticleInfrastructureRestApiModuleTestCase;
import org.dalvarez.shop.shop_core.shop_common.shared.domain.log.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class ArticleGetByIdControllerShouldItTestCase extends ArticleInfrastructureRestApiModuleTestCase {

    protected Logger log;

    protected ArticleGetByIdControllerShouldItTestCase(@Autowired ArticleRepository repository,
                                                       @Autowired Logger log) {
        super(repository, log);
        this.log = log;
    }

    @Test
    void shouldGetById() throws Exception {
        final Article expected = data.get(0);

        final ArticleResponse response = shouldgetById(expected.id().value(), ArticleResponse.class);

        assertEquals(ArticleResponse.fromArticle(expected), response);
    }

    @Test
    void shouldNotgetByIdCauseNotExist() throws Exception {
        shouldNotgetById(ArticleMother.random()
                                        .id().value());
    }

}
