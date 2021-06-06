package org.dalvarez.shop.core.shared.domain.validation;

public class NotNullValidator implements FieldValidator {

    private static NotNullValidator instance;

    private static final String CAUSE_MESSAGE = "value is null";

    private NotNullValidator() {

    }

    public static NotNullValidator getInstance() {
        if (instance == null)
            instance = new NotNullValidator();

        return instance;
    }

    @Override
    public String getCause() {
        return CAUSE_MESSAGE;
    }

    @Override
    public void validate(final Field<Object> field) throws ValidationNotPassedException {
        if (field.getValue() == null) {
            throw new ValidationNotPassedException(
                    field.getName(),
                    null,
                    CAUSE_MESSAGE,
                    this.getClass()
            );
        }
    }

}
