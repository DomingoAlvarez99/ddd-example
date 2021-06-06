package org.dalvarez.shop.core.shared.domain.validation;

abstract class NotEmptyValidator implements FieldValidator {

    protected static final String CAUSE_MESSAGE = "value is empty";

    @Override
    public abstract void validate(final Field<Object> field) throws ValidationNotPassedException;

    @Override
    public abstract String getCause();

}
