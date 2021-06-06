package org.dalvarez.shop.core.shared.domain.validation;

public class GenericNotEmptyValidator extends NotEmptyValidator {

    private static GenericNotEmptyValidator instance;

    private static final FieldValidator notNullValidator = NotNullValidator.getInstance();

    private GenericNotEmptyValidator() {

    }

    public static GenericNotEmptyValidator getInstance() {
        if (instance == null)
            instance = new GenericNotEmptyValidator();

        return instance;
    }

    @Override
    public String getCause() {
        return CAUSE_MESSAGE;
    }

    @Override
    public void validate(final Field<Object> field) throws ValidationNotPassedException {
        notNullValidator.validate(field);

        if (field.getValue()
                 .toString()
                 .isBlank()) {
            throw new ValidationNotPassedException(
                    field.getName(),
                    field.getValue(),
                    CAUSE_MESSAGE,
                    this.getClass()
            );
        }
    }

}
