package org.dalvarez.shop.core.shared.domain.validation;

import org.dalvarez.shop.core.shared.domain.exception.BadRequestException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class Validator<T> {

    private final Map<String, FieldValidator> fieldValidators;

    private final Class<T> aggregateClass;

    protected Validator(final Map<String, FieldValidator> fieldValidators,
                        final Class<T> aggregateClass) {
        this.fieldValidators = fieldValidators;
        this.aggregateClass = aggregateClass;
    }

    protected void validate(final List<Field<Object>> fields) throws InvalidObjectException {
        final List<String> validationErrors = new ArrayList<>();

        fields.forEach(field -> {
            final Optional<FieldValidator> fieldValidator = Optional.ofNullable(fieldValidators.get(field.getName()));

            fieldValidator.ifPresent(fv -> {
                try {
                    fv.validate(field);
                } catch (final ValidationNotPassedException e) {
                    validationErrors.add(e.getMessage());
                }
            });
        });

        if (!validationErrors.isEmpty())
            throw new InvalidObjectException(aggregateClass, validationErrors);
    }

    protected void validate() {
        try {
            validate(getFields());
        } catch (InvalidObjectException e) {
            throw new BadRequestException(e);
        }
    }

    protected abstract List<Field<Object>> getFields();

}
