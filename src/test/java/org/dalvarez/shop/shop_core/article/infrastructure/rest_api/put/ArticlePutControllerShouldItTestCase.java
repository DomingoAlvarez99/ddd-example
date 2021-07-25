package org.dalvarez.shop.shop_core.article.infrastructure.rest_api.put;

import org.dalvarez.shop.shop_core.article.application.ArticleResponse;
import org.dalvarez.shop.shop_core.article.domain.Article;
import org.dalvarez.shop.shop_core.article.domain.ArticleRepository;
import org.dalvarez.shop.shop_core.article.infrastructure.ArticleInfrastructureRestApiModuleTestCase;
import org.dalvarez.shop.shop_core.article.infrastructure.rest_api.controller.put.ArticlePutRequest;
import org.dalvarez.shop.shop_common.log.domain.Logger;
import org.dalvarez.shop.shop_core.shared.domain.UuidMother;
import org.dalvarez.shop.shop_core.shared.domain.util.RandomElementPicker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class ArticlePutControllerShouldItTestCase extends ArticleInfrastructureRestApiModuleTestCase {

    private static final Integer STOCK = 100;

    private static final Double PRICE = 2.25;

    private static final String NAME = "Dark souls 3";

    private static final String DESCRIPTION = "Desc 1";

    protected Logger log;

    protected ArticlePutControllerShouldItTestCase(@Autowired ArticleRepository repository,
                                                   @Autowired Logger log) {
        super(repository, log);
        this.log = log;
    }

    @Test
    void shouldPutAnArticle() throws Exception {
        final Article article = data.get(1);

        final ArticlePutRequest request = new ArticlePutRequest(
                STOCK,
                PRICE,
                NAME,
                DESCRIPTION
        );

        final ArticleResponse expected = shouldPut(request, ArticleResponse.class, article.getUuid());

        log.info("asdsd| {}", expected.toString());
        final ArticleResponse actual = shouldGetByUuid(expected.getUuid(), ArticleResponse.class);

        assertEquals(expected.getUuid(), actual.getUuid());
    }

    @Test
    void shouldNotPutAnArticleCauseNotExist() throws Exception {
        final ArticlePutRequest request = new ArticlePutRequest(
                STOCK,
                PRICE,
                NAME,
                DESCRIPTION
        );

        final String uuid = UuidMother.randomGeneration(888);

        shouldNotPutCauseNotExist(request, uuid);
    }

}
