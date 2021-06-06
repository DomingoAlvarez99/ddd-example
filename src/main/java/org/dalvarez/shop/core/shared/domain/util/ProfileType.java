package org.dalvarez.shop.core.shared.domain.util;

public enum ProfileType {

    DEV(ProfileType.DEV_VALUE),
    DEFAULT(ProfileType.DEFAULT_VALUE),
    TEST(ProfileType.TEST_VALUE);

    private static final String DEV_VALUE = "DEV";

    private static final String DEFAULT_VALUE = "DEFAULT";

    private static final String TEST_VALUE = "TEST";

    private final String value;

    ProfileType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
