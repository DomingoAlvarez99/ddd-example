package org.dalvarez.shop.shop_core.article.infrastructure.rest_api.put;

import org.dalvarez.shop.shop_core.article.infrastructure.rest_api.controller.put.ArticlePutRequest;
import org.dalvarez.shop.shop_core.shared.infrastructure.shared.ContextTestCase;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.BadRequestException;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.InvalidObjectException;
import org.dalvarez.shop.shop_common.shared.domain.log.Logger;
import org.dalvarez.shop.shop_common.shared.domain.util.StringUtils;
import org.dalvarez.shop.shop_core.shared.domain.UuidMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class ArticlePutRequestShouldTestCase extends ContextTestCase {

    private static final String UUID = UuidMother.randomPick();

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
        ).toArticle(UUID);
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
                ).toArticle("kekw")
        );

        final List<String> errors = InvalidObjectException.asListOfErrors(exception.getMessage());

        final List<String> expectedErrors = List.of(
                "Field <uuid=kekw> has not passed the validator <UuidValidator> cause uuid not have a valid format ^0-9a-f{8}-0-9a-f{4}-0-9a-f{4}-0-9a-f{4}-0-9a-f{12}$",
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
