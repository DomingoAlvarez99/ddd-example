package org.dalvarez.shop.core.shared.domain.validation;

import org.dalvarez.shop.core.shared.domain.log.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

class UuidValidatorShouldTestCase extends BaseValidatorTestCase<String> {

    private UuidValidatorShouldTestCase(@Autowired Logger log) {
        super(log, UuidValidator.getInstance());
    }

    @Test
    void shouldPassTheValidation() throws ValidationNotPassedException {
        final String uuid = UUID.randomUUID()
                                .toString();

        processValidationPassed(uuid);
    }

    @Test
    void shouldNotPassTheValidation() {
        final String invalidUuid = UUID.randomUUID()
                                       .toString()
                                       .replaceAll("-", "|");

        processValidationNotPassed(invalidUuid);
    }

    @Test
    void shouldNotPassTheValidationCauseIsNull() {
        processValidationNotPassed(null, NotNullValidator.getInstance());
    }

}
