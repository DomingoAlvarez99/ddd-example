package org.dalvarez.shop.shop_core.article.infrastructure.rest_api.get.by_uuid;

import org.dalvarez.shop.shop_core.article.application.ArticleResponse;
import org.dalvarez.shop.shop_core.article.domain.Article;
import org.dalvarez.shop.shop_core.article.domain.ArticleMother;
import org.dalvarez.shop.shop_core.article.domain.ArticleRepository;
import org.dalvarez.shop.shop_core.article.infrastructure.ArticleInfrastructureRestApiModuleTestCase;
import org.dalvarez.shop.shop_shared.log.domain.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class ArticleGetByUuidControllerShouldItTestCase extends ArticleInfrastructureRestApiModuleTestCase {

    protected Logger log;

    protected ArticleGetByUuidControllerShouldItTestCase(@Autowired ArticleRepository repository,
                                                         @Autowired Logger log) {
        super(repository, log);
        this.log = log;
    }

    @Test
    void shouldGetByUuid() throws Exception {
        final Article expected = data.get(0);

        final ArticleResponse response = shouldGetByUuid(expected.getUuid(), ArticleResponse.class);

        assertEquals(expected, response.toArticle());
    }

    @Test
    void shouldNotGetByUuidCauseNotExist() throws Exception {
        shouldNotGetByUuid(ArticleMother.random()
                                        .getUuid());
    }

}
