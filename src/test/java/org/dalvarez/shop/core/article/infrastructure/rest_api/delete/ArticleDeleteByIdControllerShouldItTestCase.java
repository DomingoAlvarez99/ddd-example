package org.dalvarez.shop.core.article.infrastructure.rest_api.delete;

import org.dalvarez.shop.core.article.domain.Article;
import org.dalvarez.shop.core.article.domain.ArticleRepository;
import org.dalvarez.shop.core.article.infrastructure.ArticleInfrastructureRestApiModuleTestCase;
import org.dalvarez.shop.shared.log.domain.Logger;
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

        shouldDeleteById(expected.getId());

        shouldNotGetById(expected.getId());
    }

}
