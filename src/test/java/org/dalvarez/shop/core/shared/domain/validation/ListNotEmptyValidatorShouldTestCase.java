package org.dalvarez.shop.core.shared.domain.validation;

import org.dalvarez.shop.core.shared.domain.log.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

final class ListNotEmptyValidatorShouldTestCase extends BaseValidatorTestCase<List<?>> {

    private ListNotEmptyValidatorShouldTestCase(@Autowired Logger log) {
        super(log, ListNotEmptyValidator.getInstance());
    }

    @Test
    void shouldPassTheValidation() throws ValidationNotPassedException {
        final List<String> list = List.of("Element 1");

        processValidationPassed(list);
    }

    @Test
    void shouldNotPassTheValidationCauseIsEmpty() {
        processValidationNotPassed(List.of());
    }

    @Test
    void shouldNotPassTheValidationCauseIsNull() {
        processValidationNotPassed(null, NotNullValidator.getInstance());
    }

}
