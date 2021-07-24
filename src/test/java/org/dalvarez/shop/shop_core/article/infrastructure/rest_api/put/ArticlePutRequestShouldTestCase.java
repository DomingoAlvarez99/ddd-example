package org.dalvarez.shop.shop_core.article.infrastructure.rest_api.put;

import org.dalvarez.shop.shop_core.article.infrastructure.rest_api.controller.put.ArticlePutRequest;
import org.dalvarez.shop.shop_core.shared.ContextTestCase;
import org.dalvarez.shop.shop_shared.shared.infrastructure.validation.BadRequestException;
import org.dalvarez.shop.shop_shared.shared.infrastructure.validation.InvalidObjectException;
import org.dalvarez.shop.shop_shared.log.domain.Logger;
import org.dalvarez.shop.shop_shared.shared.domain.util.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class ArticlePutRequestShouldTestCase extends ContextTestCase {

    private static final Long ID = 1L;

    private static final Integer STOCK = 100;

    private static final Double PRICE = 2.25;

    private static final String NAME = "Dark souls 3";

    private static final String DESCRIPTION = "Desc 1";

    @Autowired
    protected Logger log;

    @Test
    void shouldCreateAPutRequestArticle() {
        new ArticlePutRequest(
                STOCK,
                PRICE,
                NAME,
                DESCRIPTION
        ).toArticle(ID);
    }

    @Test
    void shouldNotCreateAPutRequestArticleCauseHasInvalidFields() {
        final BadRequestException exception = Assertions.assertThrows(
                BadRequestException.class,
                () -> new ArticlePutRequest(
                        -1,
                        333333.25,
                        StringUtils.EMPTY,
                        null
                ).toArticle(-100L)
        );

        final List<String> errors = InvalidObjectException.asListOfErrors(exception.getMessage());

        final List<String> expectedErrors = List.of(
                "Field <id=-100> has not passed the validator <IdValidator> cause the value is out of range <minValue=1.00>, <maxValue=179769313486231570000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.00>",
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
