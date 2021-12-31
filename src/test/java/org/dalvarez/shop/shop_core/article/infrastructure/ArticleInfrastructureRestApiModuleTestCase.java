package org.dalvarez.shop.shop_core.article.infrastructure;

import org.dalvarez.shop.shop_core.article.domain.model.Article;
import org.dalvarez.shop.shop_core.article.domain.ArticleMother;
import org.dalvarez.shop.shop_core.article.domain.port.ArticleRepository;
import org.dalvarez.shop.shop_core.article.infrastructure.rest_api.controller.ArticleApiController;
import org.dalvarez.shop.shop_core.shared.infrastructure.rest_api.InfrastructureRestApiModuleTestCase;
import org.dalvarez.shop.shop_core.shop_common.shared.domain.log.Logger;
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
