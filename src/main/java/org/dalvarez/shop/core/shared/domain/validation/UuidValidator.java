package org.dalvarez.shop.core.shared.domain.validation;

import java.util.List;
import java.util.regex.Pattern;

public final class UuidValidator implements FieldValidator {

    private static UuidValidator instance;

    public static final String UUID_FIELD_NAME = "";

    private static final String UUID_RE = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";

    private static final Pattern uuidPattern = Pattern.compile(UUID_RE);

    private static final List<FieldValidator> validators = List.of(
            NotNullValidator.getInstance(),
            GenericNotEmptyValidator.getInstance()
    );

    private UuidValidator() {

    }

    private static final String CAUSE_MESSAGE = "uuid not have a valid format [" + UUID_RE + "]";

    public static UuidValidator getInstance() {
        if (instance == null)
            instance = new UuidValidator();

        return instance;
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

        if (!uuidPattern.matcher(field.getValue()
                                      .toString())
                        .matches())
            throw new ValidationNotPassedException(
                    field.getName(),
                    field.getValue(),
                    CAUSE_MESSAGE,
                    this.getClass()
            );
    }

}
