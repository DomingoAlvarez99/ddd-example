package org.dalvarez.shop.shop_core.article.infrastructure.rest_api.get.by_id;

import org.dalvarez.shop.shop_core.article.application.ArticleResponse;
import org.dalvarez.shop.shop_core.article.domain.Article;
import org.dalvarez.shop.shop_core.article.domain.ArticleRepository;
import org.dalvarez.shop.shop_core.article.infrastructure.ArticleInfrastructureRestApiModuleTestCase;
import org.dalvarez.shop.shop_shared.log.domain.Logger;
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

        final ArticleResponse response = shouldGetById(expected.getId(), ArticleResponse.class);

        assertEquals(expected, response.toArticle());
    }

    @Test
    void shouldNotGetByIdCauseNotExist() throws Exception {
        shouldNotGetById(2000L);
    }

}
