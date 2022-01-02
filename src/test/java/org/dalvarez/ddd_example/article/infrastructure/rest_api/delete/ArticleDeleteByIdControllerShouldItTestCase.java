package org.dalvarez.ddd_example.article.infrastructure.rest_api.delete;

import org.dalvarez.ddd_example.article.infrastructure.ArticleInfrastructureRestApiModuleTestCase;
import org.dalvarez.ddd_example.shared.domain.log.Logger;
import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.article.domain.repository.ArticleRepository;
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
