package org.dalvarez.ddd_example.article.infrastructure.rest_api.delete;

import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.article.domain.repository.ArticleRepository;
import org.dalvarez.ddd_example.article.infrastructure.ArticleInfrastructureRestApiModuleTestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

final class ArticleDeleteByIdControllerShouldItTestCase extends ArticleInfrastructureRestApiModuleTestCase {

    ArticleDeleteByIdControllerShouldItTestCase(@Autowired ArticleRepository repository) {
        super(repository);
    }

    @Test
    void shouldDeleteById() throws Exception {
        final Article expected = data.get(10);

        shouldDeleteById(expected.id().value());

        shouldNotgetById(expected.id().value());
    }

}
