package org.dalvarez.shop.core.shared.domain.validation;

import java.util.List;

public class InRangeValidator implements FieldValidator {

    private final Number minValue;

    private final Number maxValue;

    private final Integer causeDecimals;

    private static final List<FieldValidator> validators = List.of(
            NotNullValidator.getInstance(),
            GenericNotEmptyValidator.getInstance()
    );

    public InRangeValidator(final Number minValue,
                            final Number maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        causeDecimals = 2;
    }

    public InRangeValidator(final Number minValue,
                            final Number maxValue,
                            final Integer causeDecimals) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.causeDecimals = causeDecimals;
    }

    @Override
    public void validate(final Field<Object> field) throws ValidationNotPassedException {
        validate(
                field,
                this.getClass()
        );
    }

    protected void validate(final Field<Object> field,
                            final Class<? extends FieldValidator> clazz) throws ValidationNotPassedException {
        for (FieldValidator validator : validators) {
            validator.validate(field);
        }

        final double valueParsed = Double.parseDouble(field.getValue()
                                                           .toString());

        if (valueParsed < minValue.doubleValue() || valueParsed > maxValue.doubleValue()) {
            throw new ValidationNotPassedException(
                    field.getName(),
                    field.getValue(),
                    getCause(),
                    clazz
            );
        }
    }

    @Override
    public String getCause() {
        final String format = "the value is out of range [<minValue=%." + causeDecimals + "f>, <maxValue=%." + causeDecimals + "f>]";

        return String.format(
                format,
                minValue.doubleValue(),
                maxValue.doubleValue()
        );
    }

}
