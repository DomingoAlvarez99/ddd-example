package org.dalvarez.shop.core.shared.domain.util;

import java.math.BigDecimal;

public final class DoubleUtils {

    public static Double removeTrailingZeros(final Double d) {
        return BigDecimal.valueOf(d)
                         .stripTrailingZeros()
                         .doubleValue();
    }

}
