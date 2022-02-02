package org.dalvarez.ddd_example.article.infrastructure.rest_api.post;

import org.dalvarez.ddd_example.article.application.ArticleRequest;
import org.dalvarez.ddd_example.article.domain.repository.ArticleRepository;
import org.dalvarez.ddd_example.article.infrastructure.ArticleInfrastructureRestApiModuleTestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

final class ArticlePostControllerShouldItTestCase extends ArticleInfrastructureRestApiModuleTestCase {

    private static final Integer STOCK = 100;

    private static final Double PRICE = 2.25;

    private static final String NAME = "Dark souls 3";

    private static final String DESCRIPTION = "Desc 1";

    ArticlePostControllerShouldItTestCase(@Autowired ArticleRepository repository) {
        super(repository);
    }

    @Test
    void shouldPostAnArticle() throws Exception {
        final ArticleRequest request = ArticleRequest.of(
                STOCK,
                PRICE,
                NAME,
                DESCRIPTION,
                null
        );

        shouldPost(request);
    }

}
