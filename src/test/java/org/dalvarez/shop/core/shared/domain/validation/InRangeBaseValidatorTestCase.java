package org.dalvarez.shop.core.shared.domain.validation;

import org.dalvarez.shop.core.shared.domain.log.Logger;

class InRangeBaseValidatorTestCase<T extends Number> extends BaseValidatorTestCase<T> {

    InRangeBaseValidatorTestCase(final Logger log,
                                 final T minValue,
                                 final T maxValue) {
        super(log, new InRangeValidator(minValue, maxValue));
    }

    void shouldPassTheValidation(final T value) throws ValidationNotPassedException {
        processValidationPassed(value);
    }

    void shouldNotPassTheValidationCauseIsNull() {
        processValidationNotPassed(null, NotNullValidator.getInstance());
    }

    void shouldNotPassValidationCauseIsLower(final T value) {
        processValidationNotPassed(value);
    }

    void shouldNotPassValidationCauseIsHigher(final T value) {
        processValidationNotPassed(value);
    }

}
