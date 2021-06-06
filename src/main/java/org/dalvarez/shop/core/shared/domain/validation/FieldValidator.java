package org.dalvarez.shop.core.shared.domain.validation;

public interface FieldValidator {

    void validate(final Field<Object> field) throws ValidationNotPassedException;

    String getCause();

}
