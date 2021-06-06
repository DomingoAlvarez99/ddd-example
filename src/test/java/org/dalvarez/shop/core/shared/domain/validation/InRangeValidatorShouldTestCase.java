package org.dalvarez.shop.core.shared.domain.validation;

import org.dalvarez.shop.core.shared.domain.log.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

final class InRangeValidatorShouldTestCase extends InRangeBaseValidatorTestCase<Double> {

    private InRangeValidatorShouldTestCase(@Autowired Logger log) {
        super(log, 100.25D, 1000.50D);
    }

    @Test
    void shouldPassTheValidation() throws ValidationNotPassedException {
        final Double value = 100.25D;

        shouldPassTheValidation(value);
    }

    @Test
    void shouldNotPassValidationCauseIsNull() {
        super.shouldNotPassTheValidationCauseIsNull();
    }

    @Test
    void shouldNotPassValidationCauseIsLower() {
        final Double value = 0.55D;

        shouldNotPassValidationCauseIsLower(value);
    }

    @Test
    void shouldNotPassValidationCauseIsHigher() {
        final Double value = 1001.75D;

        shouldNotPassValidationCauseIsHigher(value);
    }

}
