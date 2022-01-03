package org.dalvarez.ddd_example.article.infrastructure;

import org.dalvarez.ddd_example.article.domain.ArticleMother;
import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.article.domain.repository.ArticleRepository;
import org.dalvarez.ddd_example.article.infrastructure.rest_api.controller.ArticleApiController;
import org.dalvarez.ddd_example.shared.domain.log.Logger;
import org.dalvarez.ddd_example.shared.infrastructure.rest_api.InfrastructureRestApiModuleTestCase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArticleInfrastructureRestApiModuleTestCase extends InfrastructureRestApiModuleTestCase<Article, ArticleRepository> {

    private final Logger log;

    private static final String REQUEST_PATH = ArticleApiController.ARTICLES_PATH;

    protected ArticleInfrastructureRestApiModuleTestCase(@Autowired ArticleRepository repository,
                                                         @Autowired final Logger log) {
        super(repository, log, REQUEST_PATH);

        this.log = log;
    }

    @BeforeAll
    void setup() {
        final List<Article> data = ArticleMother.randomList();
        populateDatabase(data);
    }

}
