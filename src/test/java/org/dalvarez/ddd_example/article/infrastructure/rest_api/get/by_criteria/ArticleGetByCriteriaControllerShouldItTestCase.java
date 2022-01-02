package org.dalvarez.ddd_example.article.infrastructure.rest_api.get.by_criteria;

import org.dalvarez.ddd_example.article.infrastructure.ArticleInfrastructureRestApiModuleTestCase;
import org.dalvarez.ddd_example.article.application.ArticleQueryResultResponse;
import org.dalvarez.ddd_example.shared.domain.log.Logger;
import org.dalvarez.ddd_example.article.domain.repository.ArticleRepository;
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

        final ArticleQueryResultResponse response = shouldGetByCriteria(params, ArticleQueryResultResponse.class);

        assertTrue(response.totalElements() > 0);
        assertEquals(response.result()
                             .size(), (long) response.totalElements());
    }

    @Test
    void shouldNotGetByCriteriaCauseFiltersNotMatchAnything() throws Exception {
        final Map<String, List<String>> params = Map.of(
                "filtersValues", Collections.singletonList("name~eq=str")
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
