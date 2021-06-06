package org.dalvarez.shop.core.shared.domain.validation;

public final class IdValidator extends InRangeValidator {

    private static IdValidator instance;

    static final Double MAX_VALUE = Double.MAX_VALUE;

    static final Double MIN_VALUE = 1D;

    private static final Integer CAUSE_DECIMALS = 2;

    private IdValidator() {
        super(
                MIN_VALUE,
                MAX_VALUE,
                CAUSE_DECIMALS
        );
    }

    @Override
    public void validate(final Field<Object> field) throws ValidationNotPassedException {
        validate(field, this.getClass());
    }

    public static IdValidator getInstance() {
        if (instance == null)
            instance = new IdValidator();

        return instance;
    }

}
