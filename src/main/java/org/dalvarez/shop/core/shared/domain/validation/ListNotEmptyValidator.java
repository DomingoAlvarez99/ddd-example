package org.dalvarez.shop.core.shared.domain.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class ListNotEmptyValidator extends NotEmptyValidator {

    private static ListNotEmptyValidator instance;

    private static final List<FieldValidator> validators = List.of(
            NotNullValidator.getInstance()
    );

    private ListNotEmptyValidator() {

    }

    public static ListNotEmptyValidator getInstance() {
        if (instance == null)
            instance = new ListNotEmptyValidator();

        return instance;
    }

    private List<?> mapToList(final Object obj) {
        if (isCollection(obj))
            return new ArrayList<>((Collection<?>) obj);

        throw new IllegalCastingException(obj, Collection.class);
    }

    private boolean isCollection(final Object obj) {
        return obj instanceof Collection;
    }

    @Override
    public String getCause() {
        return CAUSE_MESSAGE;
    }

    @Override
    public void validate(final Field<Object> field) throws ValidationNotPassedException {
        for (FieldValidator validator : validators) {
            validator.validate(field);
        }

        final List<?> list = mapToList(field.getValue());

        if (list.isEmpty()) {
            throw new ValidationNotPassedException(
                    field.getName(),
                    field.getValue(),
                    CAUSE_MESSAGE,
                    this.getClass()
            );
        }
    }

}
