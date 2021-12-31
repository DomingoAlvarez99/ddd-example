package org.dalvarez.shop.shop_core.shop_common.shared.domain.log;

public interface Logger {

    void info(final String format,
              final Object... arguments);

    void warn(final String format,
              final Object... arguments);

    void error(final String format,
               final Object... arguments);

    void debug(final String format,
               final Object... arguments);

}
