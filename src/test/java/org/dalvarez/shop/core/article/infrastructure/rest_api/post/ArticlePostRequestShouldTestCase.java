package org.dalvarez.shop.core.article.infrastructure.rest_api.post;

import org.dalvarez.shop.core.article.infrastructure.rest_api.controller.post.ArticlePostRequest;
import org.dalvarez.shop.core.shared.ContextTestCase;
import org.dalvarez.shop.shared.shared.infrastructure.validation.BadRequestException;
import org.dalvarez.shop.shared.shared.infrastructure.validation.InvalidObjectException;
import org.dalvarez.shop.shared.log.domain.Logger;
import org.dalvarez.shop.shared.shared.domain.util.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class ArticlePostRequestShouldTestCase extends ContextTestCase {

    private static final Integer STOCK = 100;

    private static final Double PRICE = 2.25;

    private static final String NAME = "Dark souls 3";

    private static final String DESCRIPTION = "Desc 1";

    @Autowired
    protected Logger log;

    @Test
    void shouldCreateAPostRequestArticle() {
        final ArticlePostRequest article = new ArticlePostRequest(
                STOCK,
                PRICE,
                NAME,
                DESCRIPTION
        );

        assertEquals(STOCK, article.getStock());
        assertEquals(PRICE, article.getPrice());
        assertEquals(NAME, article.getName());
        assertEquals(DESCRIPTION, article.getDescription());
    }

    @Test
    void shouldNotCreateAPostRequestArticleCauseHasInvalidFields() {
        final BadRequestException exception = Assertions.assertThrows(
                BadRequestException.class,
                () -> new ArticlePostRequest(
                        -1,
                        333333.25,
                        StringUtils.EMPTY,
                        null
                ).toArticle()
        );

        final List<String> errors = InvalidObjectException.asListOfErrors(exception.getMessage());

        final List<String> expectedErrors = List.of(
                "Field <name=> has not passed the validator <GenericNotEmptyValidator> cause value is empty",
                "Field <stock=-1> has not passed the validator <InRangeValidator> cause the value is out of range <minValue=1.00>, <maxValue=100.00>",
                "Field <description=null> has not passed the validator <NotNullValidator> cause value is null",
                "Field <price=333333.25> has not passed the validator <InRangeValidator> cause the value is out of range <minValue=0.00>, <maxValue=1000.00>"
        );

        log.info(errors.stream()
                       .sorted()
                       .collect(Collectors.toList())
                       .toString());

        log.info(expectedErrors.stream()
                               .sorted()
                               .collect(Collectors.toList())
                               .toString());

        assertEquals(expectedErrors.size(), errors.size());

        assertTrue(errors.containsAll(expectedErrors));
    }

}
