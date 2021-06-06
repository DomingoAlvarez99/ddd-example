package org.dalvarez.shop.core.shared.domain.validation;

import org.dalvarez.shop.core.shared.domain.log.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class IdValidatorShouldTestCase extends BaseValidatorTestCase<Long> {

    private IdValidatorShouldTestCase(@Autowired Logger log) {
        super(log, IdValidator.getInstance());
    }

    @Test
    void shouldPassTheValidation() throws ValidationNotPassedException {
        final Long id = 1000L;

        processValidationPassed(id);
    }

    @Test
    void shouldNotPassTheValidation() {
        final Long id = 0L;

        processValidationNotPassed(id);
    }

    @Test
    void shouldNotPassTheValidationCauseIsNull() {
        processValidationNotPassed(null, NotNullValidator.getInstance());
    }

}
