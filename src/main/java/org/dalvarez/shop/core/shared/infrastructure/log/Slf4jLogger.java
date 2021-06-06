package org.dalvarez.shop.core.shared.infrastructure.log;

import org.dalvarez.shop.core.shared.domain.log.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.core.MethodParameter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

public final class Slf4jLogger implements Logger {

    private final org.slf4j.Logger logger;

    public Slf4jLogger(final InjectionPoint ip) {
        this.logger = LoggerFactory.getLogger(
                Optional.ofNullable(getMethodOrElseNull(ip.getMethodParameter()))
                        .<Class<?>>map(Method::getReturnType)
                        .orElseGet(() ->
                                           Optional.ofNullable(ip.getMethodParameter())
                                                   .map(MethodParameter::getDeclaringClass)
                                                   .orElseThrow(IllegalArgumentException::new)
                        )
        );
    }

    private Method getMethodOrElseNull(final MethodParameter methodParameter) {
        try {
            return methodParameter.getMethod();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void info(final String format,
                     final Object... arguments) {
        logger.info(format, arguments);
    }

    @Override
    public void warn(final String format,
                     final Object... arguments) {
        logger.warn(format, arguments);
    }

    @Override
    public void error(final String format,
                      final Object... arguments) {
        logger.error(format, arguments);
    }

    @Override
    public void debug(final String format,
                      final Object... arguments) {
        logger.debug(format, arguments);
    }

}
