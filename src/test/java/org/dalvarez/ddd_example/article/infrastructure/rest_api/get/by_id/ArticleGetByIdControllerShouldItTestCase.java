package org.dalvarez.ddd_example.article.infrastructure.rest_api.get.by_id;

import org.dalvarez.ddd_example.article.application.ArticleResponse;
import org.dalvarez.ddd_example.article.domain.ArticleMother;
import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.article.domain.repository.ArticleRepository;
import org.dalvarez.ddd_example.article.infrastructure.ArticleInfrastructureRestApiModuleTestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class ArticleGetByIdControllerShouldItTestCase extends ArticleInfrastructureRestApiModuleTestCase {

    ArticleGetByIdControllerShouldItTestCase(@Autowired ArticleRepository repository) {
        super(repository);
    }

    @Test
    void shouldGetById() throws Exception {
        final Article expected = data.get(0);

        final ArticleResponse response = shouldgetById(expected.id().value(), ArticleResponse.class);

        assertEquals(ArticleResponse.fromArticle(expected), response);
    }

    @Test
    void shouldNotgetByIdCauseNotExist() throws Exception {
        shouldNotgetById(ArticleMother.random().id().value());
    }

}
