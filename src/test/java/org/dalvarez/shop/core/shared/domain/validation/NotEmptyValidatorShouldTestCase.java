package org.dalvarez.shop.core.shared.domain.validation;

import org.dalvarez.shop.core.shared.domain.log.Logger;
import org.dalvarez.shop.core.shared.domain.util.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

final class NotEmptyValidatorShouldTestCase extends BaseValidatorTestCase<String> {

    private NotEmptyValidatorShouldTestCase(@Autowired Logger log) {
        super(log, GenericNotEmptyValidator.getInstance());
    }

    @Test
    void shouldPassTheValidation() throws ValidationNotPassedException {
        final String str = "str";

        processValidationPassed(str);
    }

    @Test
    void shouldNotPassValidationCauseIsEmpty() {
        processValidationNotPassed(StringUtils.EMPTY);
    }

    @Test
    void shouldNotPassTheValidationCauseIsNull() {
        processValidationNotPassed(null, NotNullValidator.getInstance());
    }

}
