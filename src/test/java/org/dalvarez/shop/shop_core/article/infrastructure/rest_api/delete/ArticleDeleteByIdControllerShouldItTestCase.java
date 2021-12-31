package org.dalvarez.shop.shop_core.article.infrastructure.rest_api.delete;

import org.dalvarez.shop.shop_core.article.domain.model.Article;
import org.dalvarez.shop.shop_core.article.domain.port.ArticleRepository;
import org.dalvarez.shop.shop_core.article.infrastructure.ArticleInfrastructureRestApiModuleTestCase;
import org.dalvarez.shop.shop_common.shared.domain.log.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

final class ArticleDeleteByIdControllerShouldItTestCase extends ArticleInfrastructureRestApiModuleTestCase {

    protected Logger log;

    protected ArticleDeleteByIdControllerShouldItTestCase(@Autowired ArticleRepository repository,
                                                          @Autowired Logger log) {
        super(repository, log);
        this.log = log;
    }

    @Test
    void shouldDeleteById() throws Exception {
        final Article expected = data.get(10);

        shouldDeleteById(expected.id().value());

        shouldNotgetById(expected.id().value());
    }

}
