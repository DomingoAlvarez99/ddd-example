package org.dalvarez.shop.shop_core.shop_common.shared.domain.value_object;

public class BoolValueObject extends ValueObject<Boolean> {

    protected BoolValueObject(final String value) {
        this(Boolean.parseBoolean(value));
    }

    protected BoolValueObject(final Boolean value) {
        super(value);
    }

    public final boolean isTrue() {
        return value();
    }

    public final boolean isFalse() {
        return !isTrue();
    }

}
