package org.dalvarez.shop.shop_core.article.infrastructure.rest_api.get.by_criteria;

import org.dalvarez.shop.shop_core.article.application.ArticleResponse;
import org.dalvarez.shop.shop_core.article.domain.ArticleRepository;
import org.dalvarez.shop.shop_core.article.infrastructure.ArticleInfrastructureRestApiModuleTestCase;
import org.dalvarez.shop.shop_common.log.domain.Logger;
import org.dalvarez.shop.shop_common.persistence.domain.criteria.QueryResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class ArticleGetByCriteriaControllerShouldItTestCase extends ArticleInfrastructureRestApiModuleTestCase {

    protected Logger log;

    protected ArticleGetByCriteriaControllerShouldItTestCase(@Autowired ArticleRepository repository,
                                                             @Autowired Logger log) {
        super(repository, log);
        this.log = log;
    }


    @Test
    void shouldGetAllArticlesByCriteria() throws Exception {
        final Map<String, List<String>> params = Map.of();

        final QueryResult<ArticleResponse> response = shouldGetByCriteria(params, QueryResult.class);

        assertTrue(response.getTotalElements() > 0);
        assertEquals(response.getResult()
                             .size(), (long) response.getTotalElements());
    }

    @Test
    void shouldNotGetByCriteriaCauseFiltersNotMatchAnything() throws Exception {
        final Map<String, List<String>> params = Map.of(
                "filtersValues", Collections.singletonList("id~gt=10000")
        );

        shouldNotGetByCriteriaCauseFiltersNotMatchAnything(params);
    }

    @Test
    void shouldNotGetByCriteriaCauseParamsAreInvalid() throws Exception {
        final Map<String, List<String>> params = Map.of(
                "filtersValues", Collections.singletonList("fieldasdds=id&operator=gt&value=10000")
        );

        shouldNotGetByCriteriaCauseParamsAreInvalid(params);
    }

}
