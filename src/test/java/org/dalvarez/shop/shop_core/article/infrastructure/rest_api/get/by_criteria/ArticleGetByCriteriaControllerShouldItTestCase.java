package org.dalvarez.shop.shop_core.article.infrastructure.rest_api.get.by_criteria;

import org.dalvarez.shop.shop_core.shop_common.persistence.application.criteria.QueryResultResponse;
import org.dalvarez.shop.shop_core.shop_common.shared.domain.log.Logger;
import org.dalvarez.shop.shop_core.shop_common.shared.domain.value_object.IntValueObject;
import org.dalvarez.shop.shop_core.shop_common.shared.domain.value_object.StringValueObject;
import org.dalvarez.shop.shop_core.shop_common.shared.domain.value_object.ValueObject;
import org.dalvarez.shop.shop_core.article.application.ArticleResponse;
import org.dalvarez.shop.shop_core.article.domain.model.Article;
import org.dalvarez.shop.shop_core.article.domain.port.ArticleRepository;
import org.dalvarez.shop.shop_core.article.infrastructure.ArticleInfrastructureRestApiModuleTestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

        final QueryResultResponse<ArticleResponse> response = shouldGetByCriteria(params, QueryResultResponse.class);

        assertTrue(response.getTotalElements() > 0);
        assertEquals(response.getResult()
                             .size(), (long) response.getTotalElements());
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
