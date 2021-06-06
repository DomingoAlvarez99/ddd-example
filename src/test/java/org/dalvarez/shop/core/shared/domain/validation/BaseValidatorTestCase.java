package org.dalvarez.shop.core.shared.domain.validation;

import org.dalvarez.shop.core.shared.ContextTestCase;
import org.dalvarez.shop.core.shared.domain.log.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BaseValidatorTestCase<T> extends ContextTestCase {

    protected final FieldValidator fieldValidator;

    protected final Logger log;

    public BaseValidatorTestCase(final Logger log,
                                 final FieldValidator fieldValidator) {
        this.log = log;
        this.fieldValidator = fieldValidator;
    }

    protected void processValidationPassed(final T value) throws ValidationNotPassedException {
        fieldValidator.validate(new Field<>(value));
    }

    protected void processValidationNotPassed(final T value) {
        processValidationNotPassed(value, fieldValidator, fieldValidator.getCause());
    }

    protected void processValidationNotPassed(final T value,
                                              final FieldValidator fieldValidator) {
        processValidationNotPassed(value, fieldValidator, fieldValidator.getCause());
    }

    private void processValidationNotPassed(final T value,
                                            final FieldValidator fieldValidator,
                                            final String cause) {
        final Field<Object> field = new Field<>(value);
        final ValidationNotPassedException exception = assertThrows(
                ValidationNotPassedException.class,
                () -> fieldValidator.validate(field)
        );

        log.info("Exception message: {}", exception.getMessage());
        final String expectedMessage = String.format(
                ValidationNotPassedException.FORMAT,
                field.getName(),
                value,
                fieldValidator.getClass()
                              .getSimpleName(),
                cause
        );

        log.info("Expected message: {}", expectedMessage);
        assertEquals(expectedMessage, exception.getMessage());
    }

}
